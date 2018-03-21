package com.glenwood.glaceemr.server.utils;

import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

@SuppressWarnings("deprecation")
public class CustomPostgreSQLDialect extends PostgreSQLDialect {
   
        public CustomPostgreSQLDialect() {
            super();
            registerFunction("glace_timezone",  new StandardSQLFunction("glace_timezone", StandardBasicTypes.STRING));
            registerFunction("to_mmddyyyy",  new StandardSQLFunction("to_mmddyyyy", StandardBasicTypes.STRING));
            registerFunction("date_diff",  new StandardSQLFunction("date_diff", StandardBasicTypes.DOUBLE));
            registerFunction("ptbalance",  new StandardSQLFunction("ptbalance", StandardBasicTypes.DOUBLE));
            registerFunction("depositbalance",  new StandardSQLFunction("depositbalance", StandardBasicTypes.DOUBLE));
            registerFunction("insbalance",  new StandardSQLFunction("insbalance", StandardBasicTypes.DOUBLE));
            registerFunction("familybalance",  new StandardSQLFunction("familybalance", StandardBasicTypes.STRING));
            registerFunction("chart_fn",  new StandardSQLFunction("chart_fn", StandardBasicTypes.STRING));
            registerFunction("insert_fn",  new StandardSQLFunction("insert_fn", StandardBasicTypes.STRING));
            registerFunction("getapptpara",  new StandardSQLFunction("getapptpara", StandardBasicTypes.STRING));
            registerFunction("replace",  new StandardSQLFunction("replace", StandardBasicTypes.STRING));
            registerFunction("split_part",  new StandardSQLFunction("split_part", StandardBasicTypes.STRING));
            registerFunction("formatphoneno",  new StandardSQLFunction("formatphoneno", StandardBasicTypes.STRING));
            registerFunction("string_agg",new StandardSQLFunction("string_agg",StandardBasicTypes.STRING));
            registerFunction("age",new StandardSQLFunction("age",StandardBasicTypes.STRING));
            registerFunction("date_part",new StandardSQLFunction("date_part",StandardBasicTypes.INTEGER));
            registerFunction("format_name",new StandardSQLFunction("format_name",StandardBasicTypes.STRING));
            registerFunction("testtableprimarykey_generator",  new StandardSQLFunction("testtableprimarykey_generator", StandardBasicTypes.STRING));
            registerFunction("substring",new StandardSQLFunction("substring",StandardBasicTypes.STRING));
        }
}