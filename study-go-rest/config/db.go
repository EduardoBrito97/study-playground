package config

import (
	"fmt"
	"log"
	"os"

	"database/sql"

	"github.com/joho/godotenv"
	_ "github.com/lib/pq"

	"com/br/simple-go-mod/migrations"
)

func DbSetup() *sql.DB {
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file")
	}

	dbHost := os.Getenv("DB_HOST")
	dbUsername := os.Getenv("DB_USERNAME")
	dbPassword := os.Getenv("DB_PASSWORD")
	dbDatabase := os.Getenv("DB_DATABASE")
	dbPort := os.Getenv("DB_PORT")

	connectionStr := fmt.Sprintf(
		"host=%s port=%s user=%s password=%s dbname=%s sslmode=disable",
		dbHost,
		dbPort,
		dbUsername,
		dbPassword,
		dbDatabase)

	db, err := sql.Open("postgres", connectionStr)
	if err != nil {
		log.Fatal(err)
	}

	err = db.Ping()
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Successful database connection.")
	return db
}

func RunMigrations(db *sql.DB) {
	migrations.CreateTaskTable(db)

	log.Println("Migrations completed successfully.")
}
