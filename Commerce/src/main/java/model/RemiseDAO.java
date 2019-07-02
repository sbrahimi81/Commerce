/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author sbrahimi
 */
public class RemiseDAO {

    private final DataSource myDataSource;

    public RemiseDAO(DataSource dataSource) {

        this.myDataSource = dataSource;

    }

    public List<RemiseEntity> getAllDiscounts() throws DAOException {

        String sql = "SELECT * FROM DISCOUNT_CODE";

        try (Connection connection = myDataSource.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
                ) {

            ArrayList<RemiseEntity> result = new ArrayList<>();

            while (rs.next()) {

                result.add(new RemiseEntity(rs.getString("DISCOUNT_CODE"), rs.getFloat("RATE")));

            }

            return result;

        } catch (SQLException ex) {

            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());

        }
    }

    public void addDiscount(String code, float taux) throws SQLException {

        String sql = "INSERT INTO DISCOUNT_CODE VALUES (?, ?)";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);) {

            stmt.setString(1, code);
            stmt.setFloat(2, taux);

            stmt.executeUpdate();

        }
    }

    public void deleteDiscount(String code) throws SQLException{

        String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, code);

            stmt.executeUpdate();

        }
        
    }

}
