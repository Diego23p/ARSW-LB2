# ARSW-LB2 / Inmortal Case

- Cesar Villamil
- Diego Puerto

#### **PART I**

1. Check the operation of the program and run it. While this occurs, run jVisualVM and check the CPU consumption of the corresponding process. Why is this consumption? Which is the responsible class?

    Este consumo se debe a que se está ejecutando en un proceso infinito. Las clases Producer y consumer son las responsales de este consumo ya que son extenciones de Thread que se ejecutan una después de la otra secuencialmente gracias al tiempo de espera proporcionado por Producer.

    ![](/image/1.jpg)

2. Make the necessary adjustments so that the solution uses the CPU more efficiently, taking into account that - for now - production is slow and consumption is fast. Verify with JVisualVM that the CPU consumption is reduced. 

    Código modificado Producer:
    
    ![](/image/pro1.jpg)
    
    Código modificado Consumer:
    
    ![](/image/con1.jpg)
    
    Consumo reducido:
    
    ![](/image/2.jpg)
 
3. Make the producer now produce very fast, and the consumer consumes slow. Taking into account that the producer knows a Stock limit (how many elements he should have, at most in the queue), make that limit be respected. Review the API of the collection used as a queue to see how to ensure that this limit is not exceeded. Verify that, by setting a small limit for the 'stock', there is no high CPU consumption or errors.

    Código modificado Producer:
    
    ![](/image/pro2.jpg)
    
    Código modificado Consumer:
    
    ![](/image/con2.jpg)
    
    Con un límite de 5 para la cola, no hay alto consumo ni errores:
    
    ![](/image/limite5.jpg)
    
#### **PART II**

2. Review the code and identify how the functionality indicated above was implemented. Given the intention of the game, an invariant should be that the sum of the life points of all players is always the same (of course, in an instant of time in which a time increase / reduction operation is not in process ). For this case, for N players, what should this value be?
    
    esta variable debería ser la multiplicación de la cantidad de N jugadores (3) por el valor M de sus vidas (100)
    
3. Run the application and verify how the ‘pause and check’ option works. Is the invariant fulfilled?

    Se observa cómo cambia la sumatoria de las vidas, el "invariante", varía:

    ![](/image/cambia.jpg)
    
4. A first hypothesis that the race condition for this function (pause and check) is presented is that the program consults the list whose values ​​it will print, while other threads modify their values. To correct this, do whatever is necessary so that, before printing the current results, all other threads are paused. Additionally, implement the ‘resume’ option.

    Corrección de la función del botón "pause and check":

    ![](/image/pauseAndCheck.jpg)
    
    Corrección de la función del botón "resume":
    
    ![](/image/resume.jpg)
    
5. Check the operation again (click the button many times). Is the invariant fulfilled or not ?.

    Se cumple el inavariante:

    ![](/image/Invariante3.jpg)
    
6. Identify possible critical regions in regards to the fight of the immortals. Implement a blocking strategy that avoids race conditions. Remember that if you need to use two or more ‘locks’ simultaneously, you can use nested synchronized blocks:

    Implemantación correcta de región crítica:
    
    ![](/image/regionCritica.jpg)
    
    
9. Once the problem is corrected, rectify that the program continues to function consistently when 100, 1000 or 10000 immortals are executed. If in these large cases the invariant begins to be breached again, you must analyze what was done in step 4.

    Cuatro "Pause and check" usando 100 inmortales:
    
    ![](/image/100.jpg)
    
    Cuatro "Pause and check" usando 1000 inmortales:
    
    ![](/image/1000.jpg)
    
    Cuatro "Pause and check" usando 10000 inmortales:
    
    ![](/image/10000.jpg)

11. To finish, implement the STOP option.

    ![](/image/stop.jpg)
