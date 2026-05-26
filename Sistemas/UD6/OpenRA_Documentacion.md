# OpenRA

> *Classic strategy games rebuilt for the modern era*

**Tipo de proyecto:** Juego / Motor de juego open source  
**Género:** Estrategia en Tiempo Real (RTS)  
**Licencia:** [[#Licencia|GNU GPL v3]]  
**Web oficial:** https://www.openra.net  
**Repositorio:** https://github.com/OpenRA/OpenRA  
**Última versión estable:** `20250330`

---

## Descripción General

OpenRA es un motor de juego RTS de código abierto que recrea los clásicos de Westwood Studios de los años 90, actualizándolos con soporte para resoluciones modernas, multijugador en red local e internet, y un sistema de mods extensible.

Nació en 2007 con el objetivo de preservar y modernizar títulos como **Command & Conquer**, **Red Alert** y **Dune 2000**. Actualmente es uno de los proyectos RTS open source más activos, con versiones estables publicadas regularmente.

### Juegos incluidos

| Mod | Facciones | Descripción |
|-----|-----------|-------------|
| `ra` — Red Alert | Aliados vs Soviéticos | Tanques, barcos, aviación. El más jugado en multijugador |
| `cnc` — Tiberian Dawn | GDI vs Brotherhood of Nod | El C&C original de 1995. Combate terrestre e infantería |
| `d2k` — Dune 2000 | Atreides / Harkonnen / Ordos | Mecánicas de especia únicas |

---

## Licencia

**GNU General Public License v3 (GPLv3)**

| Permiso | Detalle |
|---------|---------|
| Uso comercial | Permitido (derivados también deben ser GPL) |
| Modificaciones | Permitidas, deben publicarse bajo la misma licencia |
| Distribución | Libre, incluyendo binarios compilados |
| Código fuente | Completamente abierto y auditable |
| Assets gráficos | Los mods oficiales requieren datos originales de los juegos |

>  Nota sobre los assets
> El motor en sí es 100% libre. Los assets visuales y de audio de los mods oficiales (ra, cnc, d2k) se descargan automáticamente desde los juegos originales (versiones freeware liberadas por EA).

---

## Requisitos del Sistema

Requisitos muy bajos, diseñados para ejecutarse en hardware de más de una década de antigüedad.

| Componente | Mínimo |
|------------|--------|
| **SO** | Windows 7+ / macOS 10.12+ / Linux |
| **CPU** | Dual-core 1.5 GHz |
| **RAM** | 512 MB (1 GB recomendado para multijugador) |
| **GPU** | Cualquier tarjeta con soporte OpenGL 3.2 (integradas incluidas) |
| **Almacenamiento** | ~500 MB por mod |
| **Red** | LAN 100 Mbps para multijugador local |
| **Runtime** | .NET / Mono (incluido en el instalador) |

> [!tip] Rendimiento
> El motor gráfico 2D con OpenGL permite ajustar calidad visual para garantizar **60+ fps** incluso en equipos muy modestos. Funciona en laptops desde 2007.

---

## Arquitectura del Motor

El motor está escrito en **C#** sobre **.NET/Mono** y usa:
- **SDL2** — gestión de ventanas e input multiplataforma
- **OpenGL 3.2** — renderizado con aceleración GPU
- **OpenAL** — audio posicional 3D multiplataforma

### Capas principales

```
┌──────────────────────────────────────────┐
│     Capa de plataforma                   │
│     SDL2 · OpenGL · OpenAL · .NET/Mono   │
├──────────────────────────────────────────┤
│     OpenRA.Game — núcleo del motor       │
│     Game loop · World · Map · YAML       │
├────────────┬──────────┬──────┬───────────┤
│ Rendering  │ Actores  │ Red  │ UI / Lua  │
│ OpenGL 3.2 │ ECS/Trait│Lock- │ Widgets   │
│ Sprites    │ YAML     │step  │ Scripting │
├────────────┴──────────┴──────┴───────────┤
│     Mods (YAML + assets)                 │
│     ra · cnc · d2k · mods personalizados │
└──────────────────────────────────────────┘
```

### Sistema de Actores (ECS / Traits)

En lugar de jerarquías de herencia, cada unidad es un **Actor** compuesto de **Traits** independientes configurados en YAML. Un Trait define un comportamiento (movimiento, disparo, salud...).

```yaml
# Ejemplo: definición de infantería básica
E1:
  Inherits: ^Infantry
  Health:
    HP: 100
  Mobile:
    Speed: 3
  Armament:
    Weapon: RifleInfantry
  Buildable:
    Cost: 100
    Queue: Infantry
```


> Permite crear o modificar unidades **editando solo texto**, sin recompilar el motor. Es el mismo patrón Entity-Component-System (ECS) que usa Unity en su motor moderno.

---

## Multijugador y Red Local (LAN)

El multijugador es una característica central, diseñada para ser ligera y estable en redes locales.

### Modos de conexión

| Modo | Descripción |
|------|-------------|
| **LAN / red local** | Conexión directa por IP. Autodiscovery automático en la subred |
| **Online (WAN)** | Servidores maestros con lista pública. Requiere internet |
| **Servidor dedicado** | Ejecutable headless (sin GUI) para Windows, Linux o macOS |
| **Single-player** | `EchoConnection`: órdenes devueltas al cliente de forma inmediata |

### Modelo de red: Lockstep Determinista

OpenRA **no sincroniza el estado completo** entre clientes. Solo envía los *comandos* (órdenes) de cada jugador, asignados a frames concretos. Todos los clientes procesan exactamente los mismos comandos en el mismo orden.

A intervalos regulares cada cliente calcula un **hash de su estado** y lo compara con los demás. Si difieren, el motor detecta la desincronización en tiempo real. Esto hace que el tráfico de red sea mínimo.

```
Cliente A ──┐
            ├── OrderManager ──► Frame N: [mover unidad, atacar]
Cliente B ──┘                         └── mismo hash en todos
```

---

## Sistema de Mods

Un mod es una carpeta de archivos **YAML + assets** que redefine o extiende el contenido sin modificar el motor. No hace falta programar.

### Qué puede cambiar un mod

| Elemento | Archivo YAML |
|----------|-------------|
| Unidades y estadísticas | `rules/infantry.yaml`, `rules/vehicles.yaml` |
| Facciones completas | Nuevos actores con traits distintos |
| Mecánicas de recursos | Traits `Harvester`, `ResourceType` |
| Mapas y terreno | Archivos `.oramap` |
| Inteligencia artificial | Scripts Lua en `maps/` |
| Interfaz de usuario | Widget system en `chrome/` |
| Assets visuales | Sprites `.png` + secuencias en YAML |

### Mods oficiales

- **`ra`** — Red Alert. El más completo. Recomendado para clase.
- **`cnc`** — Tiberian Dawn (C&C original).
- **`d2k`** — Dune 2000.

### Mods de comunidad destacados

| Mod | Descripción |
|-----|-------------|
| **Shattered Paradise** | Expansión de Tiberian Sun. Añade facciones: Mutantes, C.A.B.A.L., Scrin |
| **OpenHV** | Juego completamente nuevo sobre el motor. Sci-fi, pixel art, licencia GPLv3 + CC |
| **OpenE2140** | Remake del clásico Earth 2140 |
| **Generals Alpha** | Recrea C&C Generals con assets de Red Alert y Tiberian Dawn |
| **Fractured Realms** | RTS original con tres facciones propias |

---

## Referencias y Recursos

- Página oficial: https://www.openra.net
- Repositorio GitHub: https://github.com/OpenRA/OpenRA
- Mods en ModDB: https://www.moddb.com/games/openra/mods
- OpenRA Mod SDK: https://github.com/OpenRA/OpenRAModSDK
- Foro oficial: https://forum.openra.net

---

