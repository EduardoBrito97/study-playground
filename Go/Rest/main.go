package main

import (
	"com/br/simple-go-mod/config"
	"com/br/simple-go-mod/handlers"
	"log"
	"net/http"
)

func main() {
	dbConnection := config.DbSetup()

	config.RunMigrations(dbConnection)

	// Whenever main stops, dbConnection.Close() is called
	defer dbConnection.Close()

	log.Fatal(http.ListenAndServe(":8080", handlers.HandleRouters(dbConnection)))
}
