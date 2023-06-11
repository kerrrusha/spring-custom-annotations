# spring-custom-annotations
Implementation of custom annotations in Spring and their post processors - @InjectRandomInt and @InjectRandomJoke. 
Also implemented three-phase constructors, added destructor and tracked context and bean states during each phase.

### Console output:

```
Constructor Phase I
Inner fields are initialized: false
Application context created: false

Constructor Phase II
Inner fields are initialized: true
Application context created: false

Constructor Phase III
Inner fields are initialized: true
Application context created: false

com.kerrrusha.springcustomannotations.SpringCustomAnnotationsApplication - Application context created: true

Why don't scientists trust atoms?
Because they make up everything!
Why don't scientists trust atoms?
Because they make up everything!
Why don't scientists trust atoms?
Because they make up everything!

com.kerrrusha.springcustomannotations.SpringCustomAnnotationsApplication - Closing context...
I'm being destroyed...(

Process finished with exit code 0

```
