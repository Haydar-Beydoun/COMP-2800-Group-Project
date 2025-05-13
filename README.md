
# Flash Fox: Side Scroller Game

Flash Fox is a 2D side-scroller where players control a fox navigating obstacles to reach the golden gem. Along the way, players encounter enemies with different abilities and can collect berries and gems to enhance their score. The game features smooth movement, parkour mechanics, and level challenges designed for an engaging experience. The goal is to complete levels while enjoying the gameplay.

<p align="center">
  <img src="https://github.com/user-attachments/assets/0a8d9bc3-bed7-4249-a8fd-df030d02eada" alt="Picture1">
</p>

## Preview
<p align="center">
  <a href="https://www.youtube.com/watch?v=PF9luTmgj6E">
    <img src="https://img.youtube.com/vi/PF9luTmgj6E/0.jpg" alt="Flash Fox Gameplay Preview">
  </a>
</p>

<p align="center">
  A short gameplay preview of Flash Fox, showcasing movement, enemies, features, and level mechanics.
</p>


## Installation

To run this game, you will need a **Java Runtime Environment (JRE)** installed on your system. This project was developed using **JDK 17**, so a JRE of **version 17 or higher** is required.

### Steps:
1. Navigate to the **"Releases"** section on the GitHub project page.
2. Download the latest **JAR file**.
3. Double-click the JAR file to launch the game.
## Features

### Level Loader
- Converts level configuration files into level objects for rendering.
- Uses a pixel map to determine level layout based on RGB values stored in a HashMap.

### Save Feature
- Automatically saves progress and screen preferences upon exiting the game.
- Reads stored data to unlock completed levels and maintain user settings.
- Creates a save file on first playthrough.

### Platformer Mechanics
- Features parkour-inspired movement and precise controls.
- Smooth physics engine with accelerating gravity, jump velocity, and movement adjustments.
- Includes a health system—taking damage from enemies reduces health, leading to a level restart upon depletion.

### Enemies
- Opossum: Moves left and right, turns at edges and walls, damages the player on contact.
- Eagle: A flying enemy that can move in various directions or remain static.

### Collectables
- Berries and gems placed throughout levels.
- Optional challenge — not required for level completion but encourage exploration.

### Music and SFX
- Each level has unique background music to enhance immersion.
- Audio cues for actions, including button clicks and level selection.
- Distinct sounds for locked and playable levels to improve user feedback.
## Screenshots

![Screenshot (190)](https://github.com/user-attachments/assets/4b4b7a90-7c2d-47c6-8e10-f865e24f3219)

![Screenshot (192)](https://github.com/user-attachments/assets/07235565-a964-446d-a0f7-895023b8829b)

![Screenshot (194)](https://github.com/user-attachments/assets/df4d079c-f642-42ae-8cef-efa2a0c8dfa9)

![Screenshot (193)](https://github.com/user-attachments/assets/d1a13fe5-ac9a-4582-b7ac-499ffec85ce6)

![Screenshot (195)](https://github.com/user-attachments/assets/f198a794-dc20-4626-ba63-740d9bade045)

