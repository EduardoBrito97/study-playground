package main

import (
	config "com/br/simple-go-mod/config"
	"log"
	"net/http"
)

func main() {
	dbConnection := config.DbSetup()

	config.RunMigrations(dbConnection)

	// Whenever main stops, dbConnection.Close() is called
	defer dbConnection.Close()

	log.Fatal(http.ListenAndServe(":8080", nil))
}
