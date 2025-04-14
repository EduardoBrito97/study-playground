package handlers

import (
	"com/br/simple-go-mod/models"
	"database/sql"
	"encoding/json"
	"net/http"
)

type TaskHandler struct {
	db *sql.DB
}

// TaskHandler ctr
func NewTaskHandler(db *sql.DB) *TaskHandler {
	return &TaskHandler{db: db}
}

func (taskHandler *TaskHandler) ReadTasks(writer http.ResponseWriter, request *http.Request) {
	var tasks []models.Task

	rows, err := taskHandler.db.Query("SELECT * FROM tasks")
	if err != nil {
		http.Error(writer, err.Error(), http.StatusInternalServerError)
		return
	}

	for rows.Next() {
		var task models.Task
		err := rows.Scan(&task.ID, &task.Title, &task.Description, &task.Status)
		if err != nil {
			http.Error(writer, err.Error(), http.StatusInternalServerError)
			return
		}

		tasks = append(tasks, task)
	}

	writer.Header().Add("Content-Type", "application/json")
	json.NewEncoder(writer).Encode(tasks)
}
