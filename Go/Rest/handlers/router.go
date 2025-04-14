package handlers

import (
	"database/sql"
	"log"

	"github.com/gorilla/mux"
)

func HandleRouters(db *sql.DB) *mux.Router {
	router := mux.NewRouter()

	taskHandler := NewTaskHandler(db)
	router.HandleFunc("/tasks", taskHandler.ReadTasks).Methods("GET")

	log.Println("Routers initialized successfully")

	return router
}
