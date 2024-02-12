# mixed language [orrery](https://en.wikipedia.org/wiki/Orrery)
---

## quicklinks
* ~/readme/**[About](#about)**
* ~/readme/**[Screenshots](#screenshots)**
* ~/readme/**[References](#references)**
<!-- aaaa do those here soon -->

---

## About
* repo is for making solar system models done in whichever language i feel like it should be in
<details><summary>show more</summary>

* this will inherit from the work done in
  - `scala_drawing`
  - `scala_ui_testing`
  - `cpp_isometric_grider`
  - `ProceduralCpp`
  - various other private repositories
  - various content done for university assignments
* aim is to keep things as simple as possible to create the base structure which can then be made into something more.
* as it stands (at repository creation), both starting points have already suffered massive feature creep and will be simplified to just:
  - 1 star at centre
  - planets orbit star
  - moons orbit planets
* ships/asteroids will be labeled as satellites and will be a milestone for each implementation
* everything should be kept as whichever portable drawing system is on hand easily for the given language that requires minimal starting time.
  - further modifications for UI and other features can be done once foundation is setup
  - changing drawing systems can then be treated as a new sub-project with the same language
  - care should be taken to avoid initial use of any system that requires pixel based drawing until this project has more implementations and familiarity
  - for the above point reason, `ncurses` should be secondary to `raylib`/`raygui` unless we find a function that handles circles or can use quick math to do it
* for inital plans, we'll probably stick to the `Kerbol System` from `Kerbal Space Program 1` as it's what is most familiar in mind right now
  - inspiration didnt necessarily come from this, as it has been a long time that I've wanted to create this project.
  - watching `For All Mankind` and playing a lot of `Kerbal space program 1` lately has meant that my interest was returned and so could dust off old notes and plans
</details>

---

## Screenshots
<ul>
  <li> Java screens:
    <details><summary>12/2/2024 <i>show/hide</i></summary>
      <img src="./screenshots/screen_20240212_1900_1.png">
      <img src="./screenshots/screen_20240212_1900_2.png">
    </details>
  </li>
  <!-- <li><i>more to come</i></li> -->
  <li><i>more to come</i></li>
</ul>

---

## References
* [Orrery](https://en.wikipedia.org/wiki/Orrery)
* [solar system model](https://en.wikipedia.org/wiki/Solar_System_model)
* [Kerbol system](https://wiki.kerbalspaceprogram.com/wiki/Kerbol_System)
* [solar system](https://en.wikipedia.org/wiki/Solar_System)

## brainstorming
### brainstorming pre-java sub-project

#### original typed brainstorm [8:30AM][11/02/2024]
<details><summary>show / hide</summary>

    Have the main
    Just does the applet code jpanel/jframe/paint loop, standard stuff

    Then you have stage, has the universe scale and a reference for sun

    The sun is a celestialBody which has things orbiting it
    Those orbits are celestialBodies or ships
    They have semimajor axis, eccentricities and stuff
    Then you have the info about the body


    Could seperate out the data from the drawn
    Celestial body is now obj with its details and orbit info
    Double linked? Or hands off data every draw?

    Maybe it doesnt know about parent so you paint. From god view and can see the parent while painting


    Painting is a depth first or bredth first? Probably bredth first


    You have the objects and orbit data
    You set that up and its just a store

    Then you can paint using that information based on current time to find where they are now

    Maybe paint is dumb and doesnt calculate at all. Just paints


    Now you could thread? Or you do update before a paint cycle. Theyre seperate accesses from main/paint loop level


    tick(long chronoIntervalMillis) which hands off all children as a “time elapsed” since last update

    Implication is u have a repaintTimeSince and updateTimeSince
    Paint is dumb and main worries about that
    But update figures out how long it takes and then predicts if it can do another before the next paint cycle?

    Maybe update only happens when we need to paint. So we just keep track of
    
    microverseOriginTimestamp
    lastPhysicsTimestamp
    lastPaintTimestamp

    If it there’s enough time since we last painted, we do a paint, but we need to ask for new context to paint so we’re not painting old content. Check buffer


    What if feature creep a buffer for paintables. So the buffer is updated with pixels of what we’re painting

    https://en.wikipedia.org/wiki/Astronomical_object




    Namables

    Star is root

    Planet is can have moons

    Moon is can have asteroids and ships

    Asteroid is can have ships

    Ships is can have nothing. Terminal point

    Star can have moon/asteroid/ship



    Maybe we categorise by size??

    Can only have object with a classification strictly less than the parent

    Becomes min heap kinda??
    Maybe make rules list


    Orbitals are strictly less than or equal to the parental body



    Floats only when it’s strictly finite small distances otherwise it’s double

    Like drawing to screen could be float

    But distances are doubles





    satellites instead of ships/asteroids



    So structure



    Main
    Applet
        Stage
        Universe
        AstronomicalBody
            semimajoraxis
            orbitalperiod
            eccentricity
            angleofinclination
            angleofmajoraxis?
            Optional<List> orbitals
</details>

