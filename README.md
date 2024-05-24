# Image Manipulation Application

## Overview

This project demonstrates the development of an image manipulation application aimed at showcasing advanced design patterns and robust software architecture. The application allows users to display an image in three different panels, each with distinct functionalities: one static and two interactive, enabling zoom, translation, and undo/redo operations.

## Objectives

The primary goals of the project include:
1. Displaying an image in three distinct panels using local files and allowing their saving in new formats.
2. Independent manipulation of the images via mouse listeners.
3. Integration of design patterns to enhance code readability and maintenance, specifically Command, Observer, Singleton, and Memento patterns.

## Key Features

- **Three Panel Display**: An image is shown in three panels, with one panel being static and the other two allowing zoom and translation adjustments.
- **Independent Manipulation**: Users can independently manipulate the images in the interactive panels, including zooming, translating, and resetting to the original state.
- **Design Patterns Implementation**: The project integrates several design patterns:
  - **Observer**: For the MVC architecture, ensuring views update automatically when models change.
  - **Memento**: For capturing and restoring the state of the images, enabling undo/redo functionality.
  - **Singleton**: Ensuring a single instance of the Caretaker class, which manages the state history.
  - **Command**: Encapsulating requests as objects, allowing parameterization of clients with queues, requests, and operations.

## Class Responsibilities and Dependencies

### Classes and Their Responsibilities

- **IPerspective**: Manages image states using Memento pattern, handles zoom and position.
- **Perspective**: Implements IPerspective, notifies observers, and uses Memento for state management.
- **Controller**: Manages user interactions and communicates with the model and view, handles undo/redo via Memento.
- **Serialization**: Manages object serialization for saving and loading states.
- **Caretaker**: Manages Memento objects for undo and redo operations, implemented as a Singleton.
- **Commands**: Encapsulate actions like zoom, translation, undo, and redo, following the Command pattern.

### Design Pattern Usage

1. **Observer Pattern**:
    - **Subject**: Perspective, OtherPerspective
    - **Observer**: Views such as PerspectivePanel and OtherPerspectivePanel
    - This pattern decouples the model from the views, enhancing modularity and ease of maintenance.

2. **Memento Pattern**:
    - **Originator**: IPerspective implementations (Perspective, OtherPerspective)
    - **Memento**: Captures the state of an image (imageIcon, position, zoomFactor)
    - **Caretaker**: Manages Memento instances, provides undo and redo functionalities.

3. **Singleton Pattern**:
    - **Caretaker**: Ensures a single instance for managing state histories, crucial for consistent undo/redo operations.

4. **Command Pattern**:
    - Defines executable actions such as ZoomInCommand, ZoomOutCommand, Translate, Undo, Redo.
    - Facilitates adding new commands without altering existing code.

## Installation

To run the application, follow these steps:

1. Clone the repository:
    ```sh
    git clone https://github.com/username/repository.git
    ```
2. Navigate to the project directory:
    ```sh
    cd repository
    ```
3. Build the project using your preferred IDE or build tool.

## Usage

1. Launch the application.
2. Load an image using the file menu.
3. Use the mouse to zoom in/out and translate the image in the interactive panels.
4. Use undo/redo commands to revert or reapply changes.

## Contribution

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch:
    ```sh
    git checkout -b feature-branch
    ```
3. Commit your changes:
    ```sh
    git commit -m 'Add new feature'
    ```
4. Push to the branch:
    ```sh
    git push origin feature-branch
    ```
5. Open a pull request.

## Conclusion

This project successfully demonstrates the application of design patterns in a practical software engineering context. The use of Observer, Memento, Singleton, and Command patterns ensures a modular, maintainable, and extensible architecture. Future improvements could include performance optimizations and additional image processing features.

## References

- Add relevant references and resources here.
... (1 línea restante)
