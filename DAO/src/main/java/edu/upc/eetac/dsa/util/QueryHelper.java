package edu.upc.eetac.dsa.util;
import org.apache.log4j.Logger;

public class QueryHelper {
    final static Logger logger = Logger.getLogger(QueryHelper.class);
    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        String field;
        int i =1;
        while (i<fields.length){
            field = fields[i];
            if (i>1) sb.append(", ");
            sb.append(field);
            i++;
        }

        sb.append(") VALUES (?");

        i=2;
        while (i<fields.length){
            sb.append(", ?");
            i++;
        }

        sb.append(")");
        logger.info(sb.toString());
        return sb.toString();
    }

    public static String createQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }
    public static String createQuerySELECTName (Object entity){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE userName = ?");

        return sb.toString();

    }
    public static String createQuerySELECTNameGame (Object entity){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE name = ?");

        return sb.toString();

    }
    public static String createQuerySELECTAll(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());

        return sb.toString();
    }
    public static String createQuerySELECTAllBynickName(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE nickNameBuyer = ?");
        return sb.toString();
    }

    public static String createQueryUPDATE(Object entity){
        // FALTA CORREGIR
        String [] fields = ObjectHelper.getFields(entity);
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("SET ");
        String field;
        int i =1;
        while (i<fields.length){
            field = fields[i];
            if (i>1) sb.append(" = ?, ");
            sb.append(field);
            i++;
        }
        sb.append(" = ?");
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQueryUPDATECOMPLEX(Object entity){
        // FALTA CORREGIR
        String [] fields = ObjectHelper.getFields(entity);
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("SET (");
        for (String field: fields) {
            sb.append(field).append(" = ?, ");
        }
        sb.append(") WHERE");
        sb.append("? = ?");

        return sb.toString();
    }

    public static String createQueryDELETE(Object entity){
        // FALTA CORREGIR

        StringBuffer sb = new StringBuffer("DELETE FROM");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("WHERE");
        sb.append("?").append(" = ");
        sb.append("?").append(" ");

        return sb.toString();
    }

}
