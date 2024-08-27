package com.example.sheltered_living;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;
import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sheltered_living.models.Feedback;
import com.example.sheltered_living.models.Manager;
import com.example.sheltered_living.models.Message;
import com.example.sheltered_living.models.StaffMember;
import com.example.sheltered_living.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.sqlite";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_TASKS = "tasks_table";
    private static final String TABLE_FEEDBACK = "feadbacks_table";
    private static final String TABLE_SHIFTS = "shifts_table";
    private static final String TABLE_STAFF_MEMBERS = "staff_members_table";
    private static final String TABLE_MANAGER = "manager_table";
    private static final String TABLE_MESSAGES = "messages_table";
    private static final String KEY_DATE = "date";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TITLE = "title";
    private static final String KEY_UID = "uid";
    private static final String KEY_ID = "id";
    private static final String KEY_MESSAGE = "messege";
    private static final String KEY_NAME = "name";
    private static final String KEY_ROOM = "room";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SHIFT= "shift";
    private static final String KEY_IMAGE= "image";


    public MySqlDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String createTasksTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + "(" +
                    KEY_ID + " LONG, " +
                    KEY_UID + " TEXT, " +
                    KEY_TITLE + " TEXT, " +
                    KEY_DESCRIPTION + " TEXT, " +
                    KEY_DATE + " TEXT, "+
                    "PRIMARY KEY (" + KEY_UID + ", " + KEY_ID +"))";
            db.execSQL(createTasksTableQuery);

            String createShiftTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_SHIFTS + "(" +
                    KEY_UID + " TEXT, " +
                    KEY_SHIFT + " TEXT, " +
                    "PRIMARY KEY (" + KEY_UID + ", " + KEY_SHIFT +"))";
            db.execSQL(createShiftTableQuery);

            String createStaffMemberTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_STAFF_MEMBERS + "(" +
                    KEY_UID+ " TEXT PRIMARY KEY, " +
                    KEY_IMAGE + " TEXT, " +
                    KEY_PHONE+ " TEXT, " +
                    KEY_NAME+ " TEXT, " +
                    KEY_EMAIL+ " TEXT)";
            db.execSQL(createStaffMemberTableQuery);

            String createFeedbackTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_FEEDBACK + "(" +
                    KEY_ID + " LONG PRIMARY KEY, " +
                    KEY_MESSAGE + " TEXT, " +
                    KEY_ROOM+ " INTEGER, " +
                    KEY_NAME + " TEXT)";
            db.execSQL(createFeedbackTableQuery);
            String createMessagesTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_MESSAGES + "(" +
                    KEY_ID + " LONG PRIMARY KEY, " +
                    KEY_MESSAGE + " TEXT, " +
                    KEY_UID+ " TEXT, " +
                    KEY_NAME +" TEXT, " +
                    KEY_IMAGE + " TEXT)";
            db.execSQL(createMessagesTableQuery);
            String createManagerTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_MANAGER + "(" +
                    KEY_UID + " TEXT PRIMARY KEY)";
            db.execSQL(createManagerTableQuery);
        } catch (Exception e){}

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void deleteTask(long id) {
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        this.getWritableDatabase().delete(TABLE_TASKS, whereClause, whereArgs);
    }

    public void deleteShit(StaffMember staffMember, String shift) {
        String whereClause = "uid = ? AND shift = ?";
        String[] whereArgs = {staffMember.getUid(), shift};
        this.getWritableDatabase().delete(TABLE_SHIFTS, whereClause, whereArgs);
    }

    public void updateTask(Task newTask) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, newTask.getId());
        String whereClause = "id = ? ";
        String[] whereArgs = {String.valueOf(newTask.getId())};
        this.getWritableDatabase().update(TABLE_TASKS, values, whereClause, whereArgs);
    }

    public void insertTask(Task task, String staffMemberUid ,SQLiteDatabase db1) {
        SQLiteDatabase db = db1 == null ? this.getWritableDatabase() : db1;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_DATE, task.getDate());
            values.put(KEY_DESCRIPTION, task.getDescription());
            values.put(KEY_TITLE, task.getTitle());
            values.put(KEY_ID, task.getId());
            values.put(KEY_UID, staffMemberUid);
            db.insertWithOnConflict(TABLE_TASKS, null,values, CONFLICT_REPLACE);
        } catch (Exception e){}
        finally {
            if (db1 == null) {
                db.close();
            }
        }
    }

    public void insertStaffMember(StaffMember staffMember, SQLiteDatabase db1) {
        SQLiteDatabase db = db1 == null ? this.getWritableDatabase() : db1;

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_UID, staffMember.getUid());
            values.put(KEY_NAME, staffMember.getName());
            values.put(KEY_PHONE, staffMember.getPhone());
            values.put(KEY_IMAGE, staffMember.getImageUrl());
            values.put(KEY_EMAIL, staffMember.getEmail());
            db.insertWithOnConflict(TABLE_STAFF_MEMBERS, null,values ,CONFLICT_IGNORE);
        }catch (Exception e){}
        finally {
            if (db1 == null) {
                db.close();
            }
        }
    }

    public void insertFeedback(Feedback feedback, SQLiteDatabase db1) {
        SQLiteDatabase db = db1 == null ? this.getWritableDatabase() : db1;

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_MESSAGE, feedback.getMessage());
            values.put(KEY_ID, feedback.getId());
            values.put(KEY_NAME, feedback.getName());
            values.put(KEY_ROOM, feedback.getRoomNumber());
            db.insertWithOnConflict(TABLE_FEEDBACK, null,values, CONFLICT_IGNORE);
        }catch (Exception e){}
        finally {
            if (db1 == null) {
                db.close();
            }
        }
    }

    public void insertManager(String uid, SQLiteDatabase db1) {
        SQLiteDatabase db = db1 == null ? this.getWritableDatabase() : db1;

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_UID, uid);
            db.insertWithOnConflict(TABLE_MANAGER, null,values, CONFLICT_IGNORE);
        }catch (Exception e){}
        finally {
            if (db1 == null) {
                db.close();
            }
        }
    }

    public void insertMessage(Message message, SQLiteDatabase db) {
        SQLiteDatabase db1 = db == null ? this.getWritableDatabase() : db;
        try {
            if (db == null) {
                db = this.getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(KEY_MESSAGE, message.getMessage());
            values.put(KEY_ID, message.getId());
            values.put(KEY_UID, message.getStaffMember().getUid());
            values.put(KEY_IMAGE, message.getStaffMember().getImageUrl());
            values.put(KEY_NAME, message.getStaffMember().getName());

            db1.insertWithOnConflict(TABLE_MESSAGES, null,values ,CONFLICT_IGNORE);
        }catch (Exception e){}
        finally {
            if (db == null) {
                db1.close();
            }
        }
    }

    public void insertShift(StaffMember staffMember, String shift, SQLiteDatabase db1) {
        SQLiteDatabase db = db1 == null ? this.getWritableDatabase() : db1;
        try {
            if (db == null) {
                db = this.getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(KEY_SHIFT, shift);
            values.put(KEY_UID, staffMember.getUid());

            db.insertWithOnConflict(TABLE_SHIFTS, null,values, CONFLICT_IGNORE);
        } catch (Exception e){}
        finally {
            if (db1 == null) {
                db.close();
            }
        }
    }

    private ArrayList<Task> getTasks(StaffMember staffMember, SQLiteDatabase db) {
        Cursor cursor = null;
        ArrayList<Task> tasks = new ArrayList<>();

        String[] columnsToRetrieve = {
                KEY_ID,
                KEY_TITLE,
                KEY_DESCRIPTION,
                KEY_DATE,
                KEY_UID
        };
        String selection = KEY_UID + "=?";
        String[] selectionArgs = {staffMember.getUid()};
        try {
            cursor = db.query(TABLE_TASKS, columnsToRetrieve, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") Task task = new Task(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
                            , cursor.getString(cursor.getColumnIndex(KEY_TITLE))
                            , cursor.getString(cursor.getColumnIndex(KEY_DATE))
                            , cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                    tasks.add(task);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            String x = e.getMessage();
        }
        finally {
            // Close the cursor after you're done using it
            if (cursor != null) {
                cursor.close();
            }
        }

        return tasks;
    }

    private ArrayList<StaffMember> getAllStaffMembers(SQLiteDatabase db) {
        Cursor cursor = null;
        ArrayList<StaffMember> list = new ArrayList<>();

        String[] columnsToRetrieve = {
                KEY_UID,
                KEY_IMAGE,
                KEY_PHONE,
                KEY_NAME,
                KEY_EMAIL
        };
        try {
            cursor = db.query(TABLE_STAFF_MEMBERS, columnsToRetrieve, null,null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") StaffMember staffMember = new StaffMember(cursor.getString(cursor.getColumnIndex(KEY_UID))
                            , cursor.getString(cursor.getColumnIndex(KEY_NAME))
                            , cursor.getString(cursor.getColumnIndex(KEY_EMAIL))
                            , cursor.getString(cursor.getColumnIndex(KEY_PHONE))
                    , cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                    list.add(staffMember);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {}
        finally {
            // Close the cursor after you're done using it
            if (cursor != null) {
                cursor.close();
            }
        }

        return list;
    }

    private ArrayList<Feedback> getAllFeedbacks(SQLiteDatabase db) {
        Cursor cursor = null;
        ArrayList<Feedback> list = new ArrayList<>();

        String[] columnsToRetrieve = {
                KEY_ID,
                KEY_MESSAGE,
                KEY_ROOM,
                KEY_NAME,
                KEY_NAME
        };

        try {
            cursor = db.query(TABLE_FEEDBACK, columnsToRetrieve, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") Feedback feedback = new Feedback(cursor.getString(cursor.getColumnIndex(KEY_MESSAGE))
                            , cursor.getString(cursor.getColumnIndex(KEY_NAME))
                            , cursor.getInt(cursor.getColumnIndex(KEY_ROOM))
                            , cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                    list.add(feedback);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {}
        finally {
            // Close the cursor after you're done using it
            if (cursor != null) {
                cursor.close();
            }
        }

        return list;
    }

    private ArrayList<Message> getAllMessages(SQLiteDatabase db) {
        Cursor cursor = null;
        ArrayList<Message> list = new ArrayList<>();

        String[] columnsToRetrieve = {
                KEY_ID,
                        KEY_MESSAGE,
                        KEY_UID,
                        KEY_NAME
        };


        try {
            cursor = db.query(TABLE_MESSAGES, columnsToRetrieve, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") StaffMember staffMember = new StaffMember(cursor.getString(cursor.getColumnIndex(KEY_UID)),
                            cursor.getString(cursor.getColumnIndex(KEY_NAME)), null, null,
                            cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                    @SuppressLint("Range") Message message = new Message(staffMember
                            , cursor.getString(cursor.getColumnIndex(KEY_MESSAGE))
                            , cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                    list.add(message);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {}
        finally {
            // Close the cursor after you're done using it
            if (cursor != null) {
                cursor.close();
            }
        }

        return list;
    }
    private Map<String, Long> getAllShifts(StaffMember staffMember, SQLiteDatabase db) {
        Cursor cursor = null;
        Map<String, Long> map = new HashMap<>();

        String[] columnsToRetrieve = {
                KEY_UID,
                KEY_SHIFT
        };
        String selection = KEY_UID + "=?";
        String[] selectionArgs = {staffMember.getUid()};

        try {
            cursor = db.query(TABLE_SHIFTS, columnsToRetrieve, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String shift = cursor.getString(cursor.getColumnIndex(KEY_SHIFT));
                    map.put(shift, Long.parseLong(shift));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {}
        finally {
            // Close the cursor after you're done using it
            if (cursor != null) {
                cursor.close();
            }
        }

        return map;
    }

    public void saveManager(Manager manager) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            for (Feedback feedback : manager.getFeedbacks()) {
                insertFeedback(feedback, db);
            }
            for (Message message : manager.getStaffMembersMessages()) {
                insertMessage(message, db);
            }
            for (StaffMember staffMember : manager.getStaffMembers()){
                insertStaffMember(staffMember, db);
                for (String shift: staffMember.getShifts().keySet()) {
                    insertShift(staffMember, shift, db);
                }
                for (Task task: staffMember.getTasks()) {
                    insertTask(task, staffMember.getUid(), db);
                }
            }
            insertManager(manager.getUid(),db);
        }catch (Exception e){}
        finally {
            db.close();
        }


    }

    public void saveStaffMember(StaffMember staffMember) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            insertStaffMember(staffMember, db);
            for (String shift: staffMember.getShifts().keySet()) {
                insertShift(staffMember, shift, db);
            }
            for (Task task: staffMember.getTasks()) {
                insertTask(task, staffMember.getUid(), db);
            }
        }catch (Exception e){}
        finally {
            db.close();
        }
    }

    public void clear() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            if (SessionManager.isManagerSession()) {
                db.execSQL("DELETE FROM " + TABLE_FEEDBACK);
                db.execSQL("DELETE FROM " + TABLE_MESSAGES);
            }

            db.execSQL("DELETE FROM " + TABLE_TASKS);
            db.execSQL("DELETE FROM " + TABLE_SHIFTS);
            db.execSQL("DELETE FROM " + TABLE_STAFF_MEMBERS);
        } catch (Exception e){}
        finally {
            db.close();
        }
    }

    public Manager getManager() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        Manager manager  = new Manager();
        String[] columnsToRetrieve = {
                KEY_UID
        };

        try {
            cursor = db.query(TABLE_SHIFTS, columnsToRetrieve, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String uid = cursor.getString(cursor.getColumnIndex(KEY_UID));
                    ArrayList<StaffMember> staffMembers = getAllStaffMembers(db);
                    for (StaffMember staffMember : staffMembers) {
                        staffMember.setShifts(getAllShifts(staffMember, db));
                        staffMember.setTasks(getTasks(staffMember, db));
                    }
                    manager.setUid(uid);
                    manager.setStaffMembers(staffMembers);
                    manager.setFeedbacks(getAllFeedbacks(db));
                    manager.setStaffMembersMessages(getAllMessages(db));

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {}
        finally {
            // Close the cursor after you're done using it
            if (cursor != null) {
                cursor.close();
            }
        }
        return manager;
    }

    public StaffMember getStaffMember() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        StaffMember staffMember = new StaffMember();

        try {
            ArrayList<StaffMember> staffMembers = getAllStaffMembers(db);
            if (staffMembers.size() > 0) {
                staffMember = staffMembers.get(0);
            }
            staffMember.setShifts(getAllShifts(staffMember, db));
            staffMember.setTasks(getTasks(staffMember, db));

        } catch (Exception e) {}
        finally {
            // Close the cursor after you're done using it
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return staffMember;
    }

    public void deleteFeedback(Feedback feedback) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(feedback.getId())};
            db.delete(TABLE_FEEDBACK, whereClause, whereArgs);
        }catch (Exception e){}
        finally {
            db.close();
        }

    }

    public void deleteMessage(Message message) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(message.getId())};
            db.delete(TABLE_MESSAGES, whereClause, whereArgs);
        }catch (Exception e){}
        finally {
            db.close();
        }
    }
}
