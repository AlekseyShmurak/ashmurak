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
        Connection connection = null;
        item.setId(this.generateId());
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
            PreparedStatement st = connection.prepareStatement("INSERT INTO items (id, name, description) VALUES (?,?,?)");
            st.setInt(1, item.getId());
            st.setString(2, item.getName());
            st.setString(3, item.getDesc());
            st.executeUpdate();
            st.close();
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return item;
    }
    /**
     * Метод редактирования заявок.
     * @param id
     * @param item
     */
    public void replace(int id, Item item) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
            PreparedStatement st = connection.prepareStatement("UPDATE items SET name = ?, description = ? WHERE id = ?");
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setInt(3, id);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * Метод удаления заявки
     * @param id
     */
    public void delete(int id) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
            PreparedStatement st = connection.prepareStatement("DELETE FROM items WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * Метод возвращает все заявки
     * @return
     */
    public List<Item> findAll() {
        Connection connection = null;
        List<Item> output = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM items");
            while (rs.next()) {
                output.add(new Item(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), rs.getString("created")));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return output;
    }
    /**
     * Метод возвращает все заявки по имени
     * @param key
     * @return
     */
    public List<Item> findByName(String key) {
        Connection connection = null;
        List<Item> output = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
            PreparedStatement st = connection.prepareStatement("SELECT * FROM items WHERE name = ?");
            st.setString(1, key);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                output.add(new Item(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), rs.getString("created")));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        Connection connection = null;
        Item output = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
            PreparedStatement st = connection.prepareStatement("SELECT * FROM items WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                output = new Item(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), rs.getString("created"));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return output;
    }

    public void deleteAllItems() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "SHMUR", "123");
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM items");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Item> getAll() {
        return this.findAll();
    }


}
