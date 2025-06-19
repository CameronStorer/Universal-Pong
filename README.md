# Universal Pong

## Table of Contents

- [About](#about)
- [Project Status](#project-status)
- [Technologies Used](#technologies-used)
  - [Java Implementation](#java-implementation)
  - [Python Implementation](#python-implementation)
  - [JavaScript Implementation](#javascript-implementation)
- [How to Run](#how-to-run)
  - [Java](#java)
  - [Python](#python)
  - [JavaScript](#javascript)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## About

"Universal Pong" is an ambitious personal project dedicated to recreating the timeless classic, Pong, across a multitude of programming languages. The primary goal is to explore the unique characteristics, libraries, and development paradigms of each language while building a functional and faithful rendition of the game. This repository serves as a central hub for all the different language implementations, showcasing the versatility and power of various programming ecosystems.

## Project Status

This project is currently under active development. As of now, implementations for Java, Python, and JavaScript are in progress or completed. More languages will be added as the project evolves.

## Technologies Used

This section outlines the specific technologies and libraries used for each language implementation.

### Java Implementation

**Directory:** `java/`

**Description:** The Java version of Pong is built using **LibGDX**, a powerful and versatile cross-platform game development framework. This allows for a robust and performant game that can potentially run on desktop, Android, iOS, and web platforms.

**Key Technologies:**

* **Java Development Kit (JDK):** Version 17+ recommended.
* **LibGDX:** A cross-platform game development framework providing functionalities for graphics, audio, input, and more.
* **Gradle:** For build automation and dependency management, which is the standard build tool for LibGDX projects.

### Python Implementation

**Directory:** `python/`

**Description:** The Python implementation focuses on simplicity and rapid development, utilizing the **Pygame** library. Pygame provides a comprehensive set of modules for handling graphics, sound, input, and other essential game development functionalities, making it ideal for creating 2D games like Pong.

**Key Technologies:**

* **Python:** Version 3.8+ recommended.
* **Pygame:** A set of Python modules designed for writing video games, providing robust functionalities for graphics, sound, and input handling.
* **(Optional) Tkinter/Pyglet:** For alternative GUI or game development approaches (exploratory).

### JavaScript Implementation

**Directory:** `javascript/`

**Description:** The JavaScript version of Pong is designed to be a web-based game, playable directly in a browser. It utilizes standard web technologies for rendering and interactivity.

**Key Technologies:**

* **HTML5:** For the game structure and canvas element.
* **CSS3:** For basic styling and layout.
* **JavaScript (ES6+):** For game logic, rendering, and event handling.
* **Canvas API:** For drawing game elements directly onto the HTML canvas.
* **(Optional) Phaser.js/PixiJS:** For more advanced 2D game development (future consideration for complex features).

## How to Run

Instructions on how to set up and run each language's version of Pong.

### Java

1.  **Navigate:** Change directory to `java/`.
2.  **Build with Gradle:** If you have Gradle installed, run `./gradlew desktop:run` (on Linux/macOS) or `gradlew.bat desktop:run` (on Windows) to build and run the desktop version. If you are using an IDE like IntelliJ IDEA or Eclipse, you can import the Gradle project and run it from there.

### Python

1.  **Navigate:** Change directory to `python/`.
2.  **Install Dependencies:** `pip install -r requirements.txt`
3.  **Run:** `python main.py` (or the name of your main game file).

### JavaScript

1.  **Navigate:** Change directory to `javascript/`.
2.  **Open in Browser:** Open the `index.html` file directly in your web browser.

## Contributing

Contributions are welcome! If you'd like to add another language implementation, improve existing ones, or fix bugs, please follow these steps:

1.  Fork the repository.
2.  Create a new branch (`git checkout -b feature/your-feature-name`).
3.  Make your changes.
4.  Commit your changes (`git commit -m 'Add new feature'`).
5.  Push to the branch (`git push origin feature/your-feature-name`).
6.  Open a Pull Request.

Please ensure your code adheres to good practices and includes relevant documentation.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any questions or inquiries, please open an issue in this repository or contact [contact@cameronstorer.com](contact@cameronstorer.com).
