<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Manipulation Application</title>
    <style>
        .center {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="center">
    <img src="src\ressources\demo.png" alt="Image Manipulation Application Screenshot">
</div>

<h1>Image Manipulation Application</h1>

<h2>Overview</h2>
<p>This project demonstrates the development of an image manipulation application aimed at showcasing advanced design patterns and robust software architecture. The application allows users to display an image in three different panels, each with distinct functionalities: one static and two interactive, enabling zoom, translation, rotation, and undo/redo operations.</p>

<h2>Objectives</h2>
<p>The primary goals of the project include:</p>
<ol>
    <li>Displaying an image in three distinct panels using local files and allowing their saving in new formats.</li>
    <li>Independent manipulation of the images via mouse listeners.</li>
    <li>Integration of design patterns to enhance code readability and maintenance, specifically Command, Observer, Singleton, and Memento patterns.</li>
</ol>

<h2>Key Features</h2>
<ul>
    <li><strong>Three Panel Display</strong>: An image is shown in three panels, with one panel being static and the other two allowing zoom, translation, and rotation adjustments.</li>
    <li><strong>Independent Manipulation</strong>: Users can independently manipulate the images in the interactive panels, including zooming, translating, rotating, and resetting to the original state.</li>
    <li><strong>Image Filters</strong>: Apply filters such as black and glitch effects to the images.</li>
    <li><strong>Design Patterns Implementation</strong>: The project integrates several design patterns:
        <ul>
            <li><strong>Observer</strong>: For the MVC architecture, ensuring views update automatically when models change.</li>
            <li><strong>Memento</strong>: For capturing and restoring the state of the images, enabling undo/redo functionality.</li>
            <li><strong>Singleton</strong>: Ensuring a single instance of the Caretaker class, which manages the state history.</li>
            <li><strong>Command</strong>: Encapsulating requests as objects, allowing parameterization of clients with queues, requests, and operations.</li>
        </ul>
    </li>
</ul>

<h2>Class Responsibilities and Dependencies</h2>

<h3>Classes and Their Responsibilities</h3>
<ul>
    <li><strong>IPerspective</strong>: Manages image states using Memento pattern, handles zoom, position, and rotation.</li>
    <li><strong>Perspective</strong>: Implements IPerspective, notifies observers, and uses Memento for state management.</li>
    <li><strong>Controller</strong>: Manages user interactions and communicates with the model and view, handles undo/redo via Memento.</li>
    <li><strong>Serialization</strong>: Manages object serialization for saving and loading states.</li>
    <li><strong>Caretaker</strong>: Manages Memento objects for undo and redo operations, implemented as a Singleton.</li>
    <li><strong>Commands</strong>: Encapsulate actions like zoom, translation, rotation, apply filter, undo, and redo, following the Command pattern.</li>
</ul>

<h3>Design Pattern Usage</h3>

<h4>Observer Pattern</h4>
<ul>
    <li><strong>Subject</strong>: Perspective, OtherPerspective</li>
    <li><strong>Observer</strong>: Views such as PerspectivePanel and OtherPerspectivePanel</li>
    <li>This pattern decouples the model from the views, enhancing modularity and ease of maintenance.</li>
</ul>

<h4>Memento Pattern</h4>
<ul>
    <li><strong>Originator</strong>: IPerspective implementations (Perspective, OtherPerspective)</li>
    <li><strong>Memento</strong>: Captures the state of an image (imageIcon, position, zoomFactor, rotation)</li>
    <li><strong>Caretaker</strong>: Manages Memento instances, provides undo and redo functionalities.</li>
</ul>

<h4>Singleton Pattern</h4>
<ul>
    <li><strong>Caretaker</strong>: Ensures a single instance for managing state histories, crucial for consistent undo/redo operations.</li>
</ul>

<h4>Command Pattern</h4>
<ul>
    <li>Defines executable actions such as ZoomInCommand, ZoomOutCommand, RotateCommand, ApplyBlackFilterCommand, ApplyGlitchCommand, Translate, Undo, Redo.</li>
    <li>Facilitates adding new commands without altering existing code.</li>
</ul>

<h2>Installation</h2>
<p>To run the application, follow these steps:</p>
<ol>
    <li>Clone the repository:</li>
    <pre><code>git clone https://github.com/username/repository.git</code></pre>
    <li>Navigate to the project directory:</li>
    <pre><code>cd repository</code></pre>
    <li>Build the project using your preferred IDE or build tool.</li>
</ol>

<h2>Usage</h2>
<ol>
    <li>Launch the application.</li>
    <li>Load an image using the file menu.</li>
    <li>Use the mouse to zoom in/out, translate, and rotate the image in the interactive panels.</li>
    <li>Apply filters using the filter menu options.</li>
    <li>Use undo/redo commands to revert or reapply changes.</li>
</ol>

<h2>Widget and Shortcuts</h2>
<p>A widget is available to display all the shortcuts and features of the application. To access the widget:</p>
<ol>
    <li>Open the widget "Help" from the toolbar </li>
    <li>The widget will display all available shortcuts and commands, including:
        <ul>
            <li><strong>Zoom In</strong>: Scroll Wheel Up </li>
            <li><strong>Zoom Out</strong>: Scroll Wheel Down </li>
            <li><strong>Translate</strong>: Drag and Drop</li>
            <li><strong>Rotate</strong>: Ctrl + R</li>
            <li><strong>Apply Black Filter</strong>: Ctrl + N</li>
            <li><strong>Apply Glitch Filter</strong>: Ctrl + G</li>
            <li><strong>Undo</strong>: Ctrl + Z</li>
            <li><strong>Redo</strong>: Ctrl + Y</li>
        </ul>
    </li>
</ol>
<h2>Conclusion</h2>
<p>This project successfully demonstrates the application of design patterns in a practical software engineering context. The use of Observer, Memento, Singleton, and Command patterns ensures a modular, maintainable, and extensible architecture. Future improvements could include performance optimizations and additional image processing features.</p>
</body>
</html>

