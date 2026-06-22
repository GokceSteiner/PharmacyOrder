psql "postgresql://postgres:postgres@localhost:5432/"
CREATE USER pharmacyorder WITH PASSWORD 'pharmacyorder';
CREATE DATABASE pharmacyorder OWNER pharmacyorder;
quit;