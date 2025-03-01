# Nyx User Guide

![Screenshot of the Nyx chatbot, with a user using the deadline and 
list commands.](Ui.png)

Looking for the next DeepSeek R1? Sorry, you won't find it here. However, what you will find is a nice 
little task manager chatbot. It won't forget your tasks, no matter how many messages you send :)

# Features

## Creating a todo task: `todo`

Creates a new todo task.

Format: `todo [name]`

Example: `todo homework`

```
Task created: [T][ ] homework
```

## Creating a deadline task: `deadline`

Creates a new deadline task.

Format: `deadline [name] -by [deadline]`

> [!NOTE]
> The deadline field must be a date in YYYY-MM-DD format. 
> For example: 2025-11-02

Example: `deadline quiz -by 2025-11-02`

```
Task created: [D][ ] quiz Deadline: 2025-11-02
```

## Creating an event task: `event`

Creates a new event task.

Format: `event [event name] -start [time] -end [time]`

> [!NOTE]
> The time fields must be a date in YYYY-MM-DD format.
> For example: 2025-11-02

Example: `event quiz -start 2025-01-15 -end 2025-01-16`

```
Task created: [E][ ] quiz  Start: Jan 15 2025 End: Jan 16 2025
```

## Listing all tasks: `list`

Displays all current tasks.

Format: `list`

```
Here is the current list of tasks:
1. [T][ ] homework
2. [D][ ] quiz Deadline: 2025-02-17
```

## Mark a task as complete: `mark`

Marks a task as complete.

Format: `mark [taskid]`

Example: `mark 1`

```
Task marked as complete: [T][X] homework
```

## Mark a task as incomplete: `unmark`

Marks a task as incomplete.

Format: `unmark [taskid]`

Example: `unmark 1`

```
Task marked as incomplete: [T][ ] homework
```

## Delete a task: `delete`

Deletes a task.

Format: `delete [taskid]`

Example: `delete 1`

```
Task deleted: [T][ ] homework
```

## Find a task: `find`

Finds all task that matches (partial matches included) the query.

Format: `find [query]`

Example: `find home`

```
Here are the matching tasks in your list:
[T][ ] homework
```

## Tag a task: `tag`

Adds a tag to a task.

Format: `tag [taskid] [tag]`

> [!NOTE]
> As of now, only one tag can be added at a time. If multiple tags
> are provided, only the first one will be added.

Example: `tag 1 cool`

```
Tag cool added to task [D][ ] quiz #cool Deadline: 2025-02-17
```

## Open the help menu: `help`

Displays a help menu with all commands and formats.

Format: `help`

```
List of commands:

Create a todo task: todo [task name]
Create a deadline task: deadline [task name] -by [deadline]
Create a event task: event [event name] -start [time] -end [time]
List all tasks: list
Mark a task as complete: mark [task id]
Mark a task as incomplete: unmark [task id]
Delete a task: delete [task id]
Find a task: find [query]
Tag a task: Tag [task id] [tag name]
Show help menu: help
Exit the application: bye
```

## Exiting the application: `bye`
Exits the application.

Format: `bye`

```
Goodbye!
```

## Acknowledgements
Frieren chibi sprites borrowed from [Guardian Tales X Frieren collab](https://www.facebook.com/permalink.php/?story_fbid=122175340070092928&id=61552787855398).
GitHub Copilot used to refine and tidy up most JavaDoc comments.
Occasional GitHub Copilot usage to autocomplete simple logic in some methods.