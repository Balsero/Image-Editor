<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Manipulation Application</title>
</head>
<body>

<h1>Image Manipulation Application</h1>

<h2>Overview</h2>
<p>This project demonstrates the development of an image manipulation application aimed at showcasing advanced design patterns and robust software architecture. The application allows users to display an image in three different panels, each with distinct functionalities: one static and two interactive, enabling zoom, translation, and undo/redo operations.</p>

<h2>Objectives</h2>
<p>The primary goals of the project include:</p>
<ol>
    <li>Displaying an image in three distinct panels using local files and allowing their saving in new formats.</li>
    <li>Independent manipulation of the images via mouse listeners.</li>
    <li>Integration of design patterns to enhance code readability and maintenance, specifically Command, Observer, Singleton, and Memento patterns.</li>
</ol>

<h2>Key Features</h2>
<ul>
    <li><strong>Three Panel Display</strong>: An image is shown in three panels, with one panel being static and the other two allowing zoom and translation adjustments.</li>
    <li><strong>Independent Manipulation</strong>: Users can independently manipulate the images in the interactive panels, including zooming, translating, and resetting to the original state.</li>
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
    <li><strong>IPerspective</strong>: Manages image states using Memento pattern, handles zoom and position.</li>
    <li><strong>Perspective</strong>: Implements IPerspective, notifies observers, and uses Memento for state management.</li>
    <li><strong>Controller</strong>: Manages user interactions and communicates with the model and view, handles undo/redo via Memento.</li>
    <li><strong>Serialization</strong>: Manages object serialization for saving and loading states.</li>
    <li><strong>Caretaker</strong>: Manages Memento objects for undo and redo operations, implemented as a Singleton.</li>
    <li><strong>Commands</strong>: Encapsulate actions like zoom, translation, undo, and redo, following the Command pattern.</li>
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
    <li><strong>Memento</strong>: Captures the state of an image (imageIcon, position, zoomFactor)</li>
    <li><strong>Caretaker</strong>: Manages Memento instances, provides undo and redo functionalities.</li>
</ul>

<h4>Singleton Pattern</h4>
<ul>
    <li><strong>Caretaker</strong>: Ensures a single instance for managing state histories, crucial for consistent undo/redo operations.</li>
</ul>

<h4>Command Pattern</h4>
<ul>
    <li>Defines executable actions such as ZoomInCommand, ZoomOutCommand, Translate, Undo, Redo.</li>
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
    <li>Use the mouse to zoom in/out and translate the image in the interactive panels.</li>
    <li>Use undo/redo commands to revert or reapply changes.</li>
</ol>

<h2>Contribution</h2>
<p>Contributions are welcome! Please follow these steps:</p>
<ol>
    <li>Fork the repository.</li>
    <li>Create a new branch:</li>
    <pre><code>git checkout -b feature-branch</code></pre>
    <li>Commit your changes:</li>
    <pre><code>git commit -m 'Add new feature'</code></pre>
    <li>Push to the branch:</li>
    <pre><code>git push origin feature-branch</code></pre>
    <li>Open a pull request.</li>
</ol>

<h2>Conclusion</h2>
<p>This project successfully demonstrates the application of design patterns in a practical software engineering context. The use of Observer, Memento, Singleton, and Command patterns ensures a modular, maintainable, and extensible architecture. Future improvements could include performance optimizations and additional image processing features.</p>

<h2>References</h2>
<p>Add relevant references and resources here.</p>

</body>
</html>
