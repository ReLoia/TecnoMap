# TecnoMap

Integrate [https://mappa.tecnocraft.net/](https://mappa.tecnocraft.net/) with your Minecraft instance to show your real-time position and server markers on a web map.

## How it Works

TecnoMap operates by running a lightweight HTTP REST server directly from your Minecraft instance

1.  **Local Server:** The mod starts a server listening on `http://localhost:8998`.
2.  **Data Endpoints:** This server provides API endpoints that expose:
    * Real-time player coordinates
    * Pre-configured markers
3.  **Web Map Integration:** The data served by TecnoMap will then be fetched using TecnoMap Extension from the web map at [https://mappa.tecnocraft.net/](https://mappa.tecnocraft.net/), allowing you to visualize your Minecraft world in real-time.

## Installation (Minecraft Mod)

*(Please adapt this section based on your mod loader and distribution method)*

1.  Download the latest `TecnoMap-<version>.jar` file from [Modrinth](https://modrinth.com/mod/tecnomap).
2.  Install Fabric for you Minecraft version (1.20.1)
3.  Place the downloaded `.jar` file into your Minecraft's `mods` folder.
4.  Launch Minecraft.

## Browser Extension

To enable the web map ([https://mappa.tecnocraft.net/](https://mappa.tecnocraft.net/)) to communicate securely with the local TecnoMap server running on your computer, a browser extension is available.

* **Function:** It acts as a bridge, allowing the website to request data from your local mod instance.
* **Availability:** The extension is available for:
    * Mozilla Firefox
    * Google Chrome (and other Chromium-based browsers like Edge, Brave, etc.)
* **Source Code:** The source code for the browser extension can be found in the [`extension` branch](https://github.com/ReLoia/TecnoMap/tree/extension) of this repository.
* **Installation:**
    * Chrome: You must install the extension manually since Chrome wants 25$ to publish it.
    * [Link to Firefox Add-ons](https://addons.mozilla.org/en-US/firefox/addon/tecnomap-extension/)

## Usage

1.  Ensure the TecnoMap mod is installed and running within your Minecraft game.
2.  Install the TecnoMap Browser Extension in your preferred web browser.
3.  Navigate to [https://mappa.tecnocraft.net/](https://mappa.tecnocraft.net/).
4.  The web map, through the extension, should automatically attempt to connect to the TecnoMap server running on `http://localhost:8998`.
5.  If successful, you should see your live player position and any configured markers appear on the map.
