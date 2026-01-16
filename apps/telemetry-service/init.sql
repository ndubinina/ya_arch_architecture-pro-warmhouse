-- Create the database if it doesn't exist
CREATE DATABASE telemetry;

-- Connect to the database
\c telemetry;

CREATE TABLE IF NOT EXISTS telemetry (
    device_id uuid PRIMARY KEY,
    value text,
    unit text,
    status text NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE IF NOT EXISTS telemetry_history (
    id SERIAL PRIMARY KEY,
    device_id uuid NOT NULL,
    value text,
    unit text,
    status text NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);
CREATE INDEX IF NOT EXISTS idx_telemetry_history_device_id ON telemetry_history(device_id);

CREATE TABLE IF NOT EXISTS device (
    device_id uuid PRIMARY KEY,
    external_id text NOT NULL,
    user_id uuid NOT NULL,
    location_id uuid,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);
CREATE INDEX IF NOT EXISTS idx_device_device_id ON device(external_id);
CREATE INDEX IF NOT EXISTS idx_device_location_id ON device(location_id);