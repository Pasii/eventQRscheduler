package com.event.qr.scheduler.eventQRScheduler.repository.impl;

import com.event.qr.scheduler.eventQRScheduler.exception.DuplicateRecordException;
import com.event.qr.scheduler.eventQRScheduler.model.QrTicket;
import com.event.qr.scheduler.eventQRScheduler.model.Seller;
import com.event.qr.scheduler.eventQRScheduler.model.TicketType;
import com.event.qr.scheduler.eventQRScheduler.repository.QrTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class QrTicketRepositoryImpl implements QrTicketRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void addTicketDetails(QrTicket qrTicket) throws SQLException, DuplicateRecordException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int existingRecordCount = 0;

        String sql0 = "select count(*) from qrticket.QR_TICKET where order_no = ? ";

        existingRecordCount = jdbcTemplate.queryForObject(sql0,Integer.class,qrTicket.getOrderNo());

        if (existingRecordCount != 0) {
            throw new DuplicateRecordException("Duplicate OrderNo");
        }


        String sql = "Insert into qrticket.QR_TICKET (ticket_id,order_no,mobile_no,qr_string, ticket_status, seller, added_date) " +
                "values (?,?,?,?,?,?,?)";

        Object[] params = new Object[] {
                qrTicket.getTicketId(),
                qrTicket.getOrderNo(),
                qrTicket.getMobileNo(),
                qrTicket.getQrString(),
                "PENDING",
                qrTicket.getSeller(),
                timestamp
        };

        jdbcTemplate.update(sql,params);

        // insert ticket types
        String sql2 = "insert into qrticket.QR_TICKET_TYPE (order_no, ticket_type,sku,variation) values (?, ?, ?, ?) ";

        jdbcTemplate.batchUpdate(sql2, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {

                ps.setString(1, qrTicket.getOrderNo());
                ps.setString(2, qrTicket.getTicketTypeList().get(i).getTicketType());
                ps.setString(3,qrTicket.getTicketTypeList().get(i).getSku());
                ps.setString(4,qrTicket.getTicketTypeList().get(i).getVaration());

            }

            @Override
            public int getBatchSize() {
                return qrTicket.getTicketTypeList().size();
            }
        });
    }

    @Override
    public void updateTicketStatus(String orderNo, String status) {

        String sql = "update qrticket.QR_TICKET set sms_status = ? where " +
                "order_no = ?";

        jdbcTemplate.update(sql,new Object[]{status,orderNo});
    }

    @Override
    public List<Seller> getSellerConfigDetails() {

        String sql = "SELECT id,seller_type, seller_status, app_key, app_secret, access_token," +
                " sms_content_1, sms_content_2, front_end_url FROM qrticket.SELLER_CONFIG " +
                "where seller_status = 'ACTIVE' ";

        RowMapper<Seller> sellerRowMapper = ((rs, rowNum) -> {
            Seller obj = new Seller();
            obj.setId(rs.getLong("ID"));
            obj.setSellerType(rs.getString("SELLER_TYPE"));
            obj.setSellerStatus(rs.getString("SELLER_STATUS"));
            obj.setAppKey(rs.getString("APP_KEY"));
            obj.setAppSecret(rs.getString("APP_SECRET"));
            obj.setAccessToken(rs.getString("ACCESS_TOKEN"));
            obj.setSmsContent1(rs.getString("SMS_CONTENT_1"));
            obj.setSmsContent2(rs.getString("SMS_CONTENT_2"));
            obj.setFrontEndUrl(rs.getString("FRONT_END_URL"));
            return obj;
        });

        return jdbcTemplate.query(sql, sellerRowMapper );
    }
}
