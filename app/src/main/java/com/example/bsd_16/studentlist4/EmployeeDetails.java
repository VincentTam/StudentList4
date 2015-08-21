package com.example.bsd_16.studentlist4;

import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class EmployeeDetails extends AppCompatActivity {

	protected TextView employeeName;
	protected TextView employeeTitle;
	protected TextView officePhone;
	protected TextView cellPhone;
	protected TextView email;
    protected int employeeId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_details);
        
        employeeId = getIntent().getIntExtra("EMPLOYEE_ID", 0);
        SQLiteDatabase db = (new DatabaseHelper(this)).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT shxt._id, shxt.firstName, shxt.lastName, shxt.title, shxt.officePhone, shxt.cellPhone, shxt.email, shxt.managerId, mgr.firstName managerFirstName, mgr.lastName managerLastName FROM employee shxt LEFT OUTER JOIN employee mgr ON shxt.managerId = mgr._id WHERE shxt._id = ?",
				new String[]{""+employeeId});

        if (cursor.getCount() == 1)
        {
        	cursor.moveToFirst();
        
	        employeeName = (TextView) findViewById(R.id.employeeName);
	        employeeName.setText(cursor.getString(cursor.getColumnIndex("firstName")) + " " + cursor.getString(cursor.getColumnIndex("lastName")));
	
	        employeeTitle = (TextView) findViewById(R.id.title);
	        employeeTitle.setText(cursor.getString(cursor.getColumnIndex("title")));

	        officePhone = (TextView) findViewById(R.id.officePhone);
	        officePhone.setText(cursor.getString(cursor.getColumnIndex("officePhone")));

	        cellPhone = (TextView) findViewById(R.id.cellPhone);
	        cellPhone.setText(cursor.getString(cursor.getColumnIndex("cellPhone")));

	        email = (TextView) findViewById(R.id.email);
	        email.setText(cursor.getString(cursor.getColumnIndex("email")));
	        
        }
 
    }
    
}