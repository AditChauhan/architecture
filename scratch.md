- How long did you spend on the coding test? What would you add to your solution if you had more time? If you didn't spend much time on the coding test then use this as an opportunity to explain what you would add.

    Despite the recruiter told me it would take about 3hr, i've spent for the task ~~ 8 hours
    That's because i think that just completion of the task is not enough to show my development skills to you
    So that i've created an application with scalable and maintainable architecture based on the modern frameworks & approaches
    To have application basis been completed it misses some features
    - Support orientation changes. Subscription cache have to survive in memory through activity recreation cycles.
    An ongoing request result with such approach would be provided based on subscription tag to corresponding newly created observer in presenter
    - Multi-module application. Application is divided by layers. We should pick out the other layers from ui layer(app)
    and put it to separate modules. It helps a lot to encapsulate implementation, increases build speed etc
    - Database caching. In current implementation user have to load restaurants information from network.
    He should have possibility to see restaurants info in offline mode, also his previous request or some preloaded date
    when new data is loading by request
    - Increase code-coverage with unit-tests (a must), ui tests by purpose. For now it is only one unit test for example.
    - pagination of data and observing for changes
    - ui. my sample definitely not about ui

- What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.

    Kotlin has a lot of modern features to use. Mostly i like delegation and extensions. You can find them in this project

- How would you track down a performance issue in production? Have you ever had to do this?

    Firstly we should avoid memory leaks, especially if we are talking about android development. LeakCanary can help us with that.
    If i faced some performance issue i would use Android Studio profilers, it has a lot of it. Unfortunately i don't have deep experience in that. That's why i try to write good code from start :D

- How would you improve the Just Eat APIs that you just used?

    - I don't know whether it already existed or not, but it will be good to have pagination for restaurants
    - In such particular case i don't need whole overpowered model of restaurants, the api may have some feature to load only required inner objects/data by request
    - documentation. An api must have a kind of documentation (swagger for ex.) about requests/responses/required&optional fields and a few worlds also would be a small present :)
    If you have such i love you
- Please describe yourself using JSON.

   I use json to talk between machines, not people