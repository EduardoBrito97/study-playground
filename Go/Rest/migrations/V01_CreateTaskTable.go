package migrations

import (
	"database/sql"
	"log"
)

func CreateTaskTable(db *sql.DB) {
	// Create the tasks table if it doesn't exist
	_, err := db.Exec(`CREATE TABLE IF NOT EXISTS tasks (
		id SERIAL PRIMARY KEY,
		title VARCHAR(100) NOT NULL,
		description TEXT,
		status BOOLEAN NOT NULL DEFAULT FALSE
	);`)

	if err != nil {
		log.Fatalf("Error creating tasks table: %v", err)
	}

}
