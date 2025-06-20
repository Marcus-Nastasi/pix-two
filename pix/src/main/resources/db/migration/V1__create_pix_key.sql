CREATE TABLE pix_key (
    id UUID PRIMARY KEY,
    type VARCHAR(9) NOT NULL,
    value VARCHAR(77) NOT NULL UNIQUE,
    account_type VARCHAR(10) NOT NULL,
    agency INTEGER NOT NULL CHECK (agency >= 0 AND agency <= 9999),
    account_number INTEGER NOT NULL CHECK (account_number >= 0 AND account_number <= 99999999),
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(45),
    active BOOLEAN DEFAULT TRUE NOT NULL,
    creation_datetime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_datetime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    inactivation_datetime TIMESTAMP
);

CREATE INDEX idx_pix_type ON pix_key(type);
CREATE INDEX idx_account ON pix_key(agency, account_number);
CREATE INDEX idx_name ON pix_key(first_name);
CREATE INDEX idx_creation_datetime ON pix_key(creation_datetime);
CREATE INDEX idx_inactivation_datetime ON pix_key(inactivation_datetime);
