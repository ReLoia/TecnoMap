# TecnoMap

Integrate [https://mappa.tecnocraft.net/](https://mappa.tecnocraft.net/) with your Minecraft instance to show your real-time position and server markers on a web map.


### Player Position
You will see your real-time position on the map. 

### Markers
Currently, these are the marked locations:
- Parco Centrale
- Legione Carabinieri "Chiaffredo Bergia"
- Palazzo Sant'Ayama
- Palazzo di Giustizia
- Banca
- Scuola
- Caserma Vigili del Fuoco "Alberto Giombini"
- Municipio
- Ospedale
- Questura
- Chiesa
- Stato Maggiore
- Guardia di Finanza
- Farmacia

---

**See the screenshot and video below for a preview of the map and the markers.**

## Installation (Minecraft Mod)

*(Please adapt this section based on your mod loader and distribution method)*

1.  Download the latest `TecnoMap-<version>.jar` file from [Modrinth](https://modrinth.com/mod/tecnomap).
2.  Install Fabric for you Minecraft version (1.20.1)
3.  Place the downloaded `.jar` file into your Minecraft's `mods` folder.
4.  Launch Minecraft.

# Usage

1.  Ensure the TecnoMap mod is installed and running within your Minecraft game.
2.  Install the [TecnoMap Browser Extension](https://github.com/ReLoia/TecnoMap/releases/latest) in your preferred web browser.
3.  Start your Minecraft game and enter the roleplay server.
4.  Navigate to [https://mappa.tecnocraft.net/](https://mappa.tecnocraft.net/).
5.  The web map, through the extension, should automatically attempt to connect to the TecnoMap server running on `http://localhost:8998`.
6.  If successful, you should see your live player position and any configured markers appear on the map.

## How it Works

TecnoMap operates by running a lightweight HTTP REST server directly from your Minecraft instance

1.  **Local Server:** The mod starts a server listening on `http://localhost:8998`.
2.  **Data Endpoints:** This server provides API endpoints that expose:
    * Real-time player coordinates
    * Pre-configured markers
3.  **Web Map Integration:** The data served by TecnoMap will then be fetched using TecnoMap Extension from the web map at [https://mappa.tecnocraft.net/](https://mappa.tecnocraft.net/), allowing you to visualize your Minecraft world in real-time.

## Browser Extension

To enable the web map ([https://mappa.tecnocraft.net/](https://mappa.tecnocraft.net/)) to communicate securely with the local TecnoMap server running on your computer, a browser extension is available.

* **Function:** It acts as a bridge, allowing the website to request data from your local mod instance.
* **Availability:** The extension is available for:
    * Mozilla Firefox (and other Gecko-based browsers like Zen)
    * Google Chrome (and other Chromium-based browsers like Edge, Brave, etc.)
* **Source Code:** The source code for the browser extension can be found in the [`extension` branch](https://github.com/ReLoia/TecnoMap/tree/extension) of this repository.
* **Installation:**
    * Chrome: You must install the extension manually [from here](https://github.com/ReLoia/TecnoMap/releases/latest) since Chrome wants 25$ to publish it.
    * [Link to Firefox Add-ons](https://addons.mozilla.org/en-US/firefox/addon/tecnomap-extension/)
  
## Screenshots

![TecnoMap Screenshot](https://hc-cdn.hel1.your-objectstorage.com/s/v3/f0052d481dc345e591aff7ad86521bb719619c41_image.png)
https://hc-cdn.hel1.your-objectstorage.com/s/v3/8a239ae1fcde29a8b65c34fa162b65e9e9621c41_tecnomappa.mp4