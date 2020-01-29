# ARSW-LB2 / Inmortal Case


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