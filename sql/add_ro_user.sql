CREATE DATABASE analytics;
CREATE USER analytics WITH PASSWORD 'analytics!@34';
GRANT CONNECT ON DATABASE analytics TO analytics;
REVOKE CONNECT ON DATABASE postgres TO analytics;
CONNECT  CONNECT ON DATABASE postgres TO analytics;
GRANT USAGE ON SCHEMA public TO analytics;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO analytics;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT ON TABLES TO analytics;