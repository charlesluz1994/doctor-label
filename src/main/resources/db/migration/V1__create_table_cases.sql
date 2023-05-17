create TABLE IF NOT EXISTS cases(
 case_id SERIAL PRIMARY KEY,
 case_description VARCHAR(500) NOT NULL,
 doctor_id INTEGER NOT NULL,
 label VARCHAR(50),
 time_to_label TIMESTAMP
);