CREATE TABLE IF NOT EXISTS appointment (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    patient_name TEXT,
    appointment_date TEXT,
    appointment_time TEXT,
    doctor_name TEXT,
    status TEXT
);
