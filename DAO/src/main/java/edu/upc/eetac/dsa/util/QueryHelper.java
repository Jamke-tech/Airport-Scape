package edu.upc.eetac.dsa.util;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        sb.append("ID");
        for (String field: fields) {
            sb.append(", ").append(field);
        }

        sb.append(") VALUES (?");

        for (String field: fields) {
            sb.append(", ?");
        }

        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQueryUPDATE(Object entity){
        // FALTA CORREGIR
        String [] fields = ObjectHelper.getFields(entity);
        StringBuffer sb = new StringBuffer("UPDATE");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("SET");
        for (String field: fields) {
            sb.append(field).append(" = ?, ");
        }

        return sb.toString();
    }

    public static String createQueryUPDATECOMPLEX(Object entity){
        // FALTA CORREGIR
        String [] fields = ObjectHelper.getFields(entity);
        StringBuffer sb = new StringBuffer("UPDATE");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("SET");
        for (String field: fields) {
            sb.append(field).append(" = ?, ");
        }
        sb.append("WHERE");
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
