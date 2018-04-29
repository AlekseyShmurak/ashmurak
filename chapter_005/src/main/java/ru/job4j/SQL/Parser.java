package ru.job4j.SQL;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Parser {
    private File genFile = new File("src/main/java/ru/job4j/SQL/1.xml");
    private File transFile = new File("src/main/java/ru/job4j/SQL/2.xml");


    public void init(int n) {
        Root root = new Root();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/ru/job4j/SQL/sample.db");
             Statement statement = connection.createStatement();) {
            connection.setAutoCommit(false);
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS test (field INTEGER )");
            statement.executeUpdate("DELETE FROM test");
            for (int i = 1; i <= n; i++) {
                String sql = "INSERT INTO test VALUES (" + i + ")";
                statement.executeUpdate(sql);
            }
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM test");){
                while (resultSet.next()) {
                    Field field = new Field();
                    field.setField(resultSet.getInt(1));
                    root.field.add(field);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(root, genFile);
//            jaxbMarshaller.marshal(root, System.out);
        } catch (JAXBException e) {
        e.printStackTrace();
        }

        TransformerFactory transFact = TransformerFactory.newInstance();
        try {
            Transformer trans = transFact.newTransformer(new StreamSource(new File("src/main/java/ru/job4j/SQL/style.xsl")));
            trans.transform(new StreamSource(genFile), new StreamResult(transFile));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public int getSum() {
        int rslt = 0;
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        try {
            DocumentBuilder builder = f.newDocumentBuilder();
            Document doc = builder.parse(transFile);
            NodeList entries = doc.getElementsByTagName("entry");
            for (int i = 0;i < entries.getLength(); i++) {
                Node entry = entries.item(i);
                int field = Integer.parseInt(entry.getAttributes().getNamedItem("field").getNodeValue());
                rslt += field;
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Результат: " + rslt);
        return  rslt;
    }

    @XmlRootElement(name = "entries")
    public static class Root {
        @XmlElement(name = "entry")
        List<Field> field = new ArrayList<>();
    }

    @XmlType
    public static class Field {
        int field;

        public int getField() {
            return field;
        }
        @XmlElement
        public void setField(int field) {
            this.field = field;
        }
    }



}

