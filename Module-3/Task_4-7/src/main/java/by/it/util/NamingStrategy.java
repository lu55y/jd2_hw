package by.it.util;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class NamingStrategy extends PhysicalNamingStrategyStandardImpl {

    private final static String TABLE_PREFIX = "T_";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        if (name== null) {
            return null;
        }

        final String newName = TABLE_PREFIX + name.getText();
        return Identifier.toIdentifier(newName);
    }

}
