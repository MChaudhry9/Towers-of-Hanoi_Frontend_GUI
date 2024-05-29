# Towers of Hanoi Project-Frontend

## This repository contains the frontend implementation of the Towers of Hanoi puzzle. It includes a graphical user interface (GUI) for interacting with the game, displaying the state of the towers visually, and providing a seamless user experience.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Setup](#setup)
- [Gameplay](#gameplay)
- [Rules Of The Game](#rules-of-the-game)

## Introduction

The Towers of Hanoi is a classic puzzle game in computer science and mathematics where the objective is to move a stack of disks from the first tower to the last tower, following specific rules. This repository provides the frontend implementation of the puzzle.

## Features

- **Reset button** that allows the user to reset the game.
- **Algorithm Solver button** that the user can click when they resign, in this case, the game is solved for them and shows a step-by-step answer.
- **Update button** where the user can enter a new number of disks they want to play with.
- Graphical user interface (GUI) for user-friendly interaction.
- Visual representation of the towers and disks.
- Seamless gameplay experience with mouse or touch input.
- Responsive design for various screen sizes.


## Requirements

- Java Development Kit (JDK) 8 or higher.
- JavaFX library (included in JDK 8 and later).
- EclipseIDE (recommended)
- WindowBuilder to make GUI: [Latest Release](https://download.eclipse.org/windowbuilder/updates/release/latest/)
  
## Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/Towers-of-Hanoi_Frontend_GUI.git
   cd Towers-of-Hanoi_Frontend_GUI
2. **Import the project into your IDE:**
   Open your IDE and import the project into it
3. **Compile and run the code:**
   Compile and run the code using your IDE's built-in tools.

   **In EclipseIDE righclick on project folder, click Run As, Click 1 Java Application**

## Gameplay 
1. Start the game:
Launch the application from your IDE.

3. Enter the number of disks:
When prompted, enter the number of disks you want to use in the puzzle.


4. Make your moves:
Use the mouse or touch input to move disks from one tower to another. Click or tap on the source and destination towers to make a move.

6. Solve the puzzle:
Continue making moves until all disks are successfully moved to the target tower.

## Rules Of The Game
The Towers of Hanoi puzzle consists of three towers and a number of disks of different sizes. The initial state has all disks stacked on the first tower in decreasing size from bottom to top. The goal is to move all the disks to the third tower, following these rules:

1. Only one disk can be moved at a time.
2. A disk can only be placed on top of a larger disk or on an empty tower.
3. The objective is to move all disks from the start tower to the end tower using the fewest moves possible.

## Screenshot
![Screenshot](path/to/your/screenshot.png)


