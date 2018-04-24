package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.*;


/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    private List<Item> cache = new ArrayList<>();
    private static final Random RANDOM = new Random();
    /**
     * Метод добавления заявок.
     * @return Item.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
             PreparedStatement st = connection.prepareStatement("INSERT INTO items (id, name, description) VALUES (?,?,?)")) {
            st.setInt(1, item.getId());
            st.setString(2, item.getName());
            st.setString(3, item.getDesc());
            st.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
    /**
     * Метод редактирования заявок.
     * @param id
     * @param item
     */
    public void replace(int id, Item item) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
             PreparedStatement st = connection.prepareStatement("UPDATE items SET name = ?, description = ? WHERE id = ?")) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setInt(3, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод удаления заявки
     * @param id
     */
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
             PreparedStatement st = connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод возвращает все заявки
     * @return
     */
    public List<Item> findAll() {
        List<Item> output = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM items")) {
            while (rs.next()) {
                output.add(new Item(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), rs.getString("created")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }
    /**
     * Метод возвращает все заявки по имени
     * @param key
     * @return
     */
    public List<Item> findByName(String key) {
        List<Item> output = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
             PreparedStatement st = connection.prepareStatement("SELECT * FROM items WHERE name = ?")) {
            st.setString(1, key);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    output.add(new Item(rs.getInt("id"), rs.getString("name"),
                            rs.getString("description"), rs.getString("created")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }
    /**
     * Метод генерирует уникальный ключ для заявки.
     * @return Уникальный ключ.
     */
    private int generateId() {
        return RANDOM.nextInt(1000000);
    }
    /**
     * Метод находит Item по id
     * @param id
     * @return Item
     */
    public Item findById(int id) {
        Item output = null;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
             PreparedStatement st = connection.prepareStatement("SELECT * FROM items WHERE id = ?");) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    output = new Item(rs.getInt("id"), rs.getString("name"),
                            rs.getString("description"), rs.getString("created"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    public void deleteAllItems() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
             Statement st = connection.createStatement()) {
            st.executeUpdate("DELETE FROM items");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAll() {
        return this.findAll();
    }


}
