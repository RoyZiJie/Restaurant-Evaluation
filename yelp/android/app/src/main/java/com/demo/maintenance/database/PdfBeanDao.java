package com.demo.maintenance.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.demo.maintenance.bean.PdfBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PDF_BEAN".
*/
public class PdfBeanDao extends AbstractDao<PdfBean, Long> {

    public static final String TABLENAME = "PDF_BEAN";

    /**
     * Properties of entity PdfBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property CreateTime = new Property(2, String.class, "createTime", false, "CREATE_TIME");
        public final static Property Path = new Property(3, String.class, "path", false, "PATH");
        public final static Property Position = new Property(4, String.class, "position", false, "POSITION");
        public final static Property UserId = new Property(5, Long.class, "userId", false, "USER_ID");
        public final static Property Type = new Property(6, int.class, "type", false, "TYPE");
        public final static Property ParentId = new Property(7, long.class, "parentId", false, "PARENT_ID");
    }


    public PdfBeanDao(DaoConfig config) {
        super(config);
    }
    
    public PdfBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PDF_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"CREATE_TIME\" TEXT," + // 2: createTime
                "\"PATH\" TEXT," + // 3: path
                "\"POSITION\" TEXT," + // 4: position
                "\"USER_ID\" INTEGER," + // 5: userId
                "\"TYPE\" INTEGER NOT NULL ," + // 6: type
                "\"PARENT_ID\" INTEGER NOT NULL );"); // 7: parentId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PDF_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PdfBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(3, createTime);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(4, path);
        }
 
        String position = entity.getPosition();
        if (position != null) {
            stmt.bindString(5, position);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(6, userId);
        }
        stmt.bindLong(7, entity.getType());
        stmt.bindLong(8, entity.getParentId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PdfBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(3, createTime);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(4, path);
        }
 
        String position = entity.getPosition();
        if (position != null) {
            stmt.bindString(5, position);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(6, userId);
        }
        stmt.bindLong(7, entity.getType());
        stmt.bindLong(8, entity.getParentId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PdfBean readEntity(Cursor cursor, int offset) {
        PdfBean entity = new PdfBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // createTime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // path
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // position
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5), // userId
            cursor.getInt(offset + 6), // type
            cursor.getLong(offset + 7) // parentId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PdfBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCreateTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPath(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPosition(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUserId(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
        entity.setType(cursor.getInt(offset + 6));
        entity.setParentId(cursor.getLong(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PdfBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PdfBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PdfBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
