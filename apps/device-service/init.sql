-- Create the database if it doesn't exist
CREATE DATABASE device;

-- Connect to the database
\c device;

CREATE TABLE IF NOT EXISTS devices (
    id uuid PRIMARY KEY,
    external_id text NOT NULL,
    user_id uuid NOT NULL,
    location_id uuid,
    name text,
    type text NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    modified_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_devices_external_id ON devices(external_id);
CREATE INDEX IF NOT EXISTS idx_devices_location_id ON devices(location_id);