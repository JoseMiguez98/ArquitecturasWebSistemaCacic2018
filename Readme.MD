# Indice

A continuación se detalla en que métodos y clases esta resuelto cada servicio

1. Inicializar la base, si no está inicializada.(?createDatabaseIfNotExist=true, parametro pasado por GET a la url de la base en persistence.xml)

2. Crear usuarios (10 usuarios) (UserDAO.persist(user,em))

3. Crear trabajos de investigación (10 trabajos de investigación) (ProjectDAO.persist(project,em))

4.
  * Consulta de todos los datos de un autor/revisor. (UserDAO.getData(id,em))
  * Dado un revisor, retornar todos sus trabajos asignados. (UserDAO.getRevisions(id,em))
  * Dado un revisor y un rango de fechas, retornar todas sus revisiones.(UserDAO.getRevisionsBetween(id,from,to,em))
  * Dado un autor, retornar todos los trabajos de investigación enviados.(UserDAO.getAllResearchWorks(id,em))

5. Consultar trabajos de investigación y sus propiedades.(ProjectDAO.getAllResearchWorksInfo(em))

6. Seleccionar trabajos de investigación de un autor y revisor en una determinada área de investigación utilizando consultas JPQL. (UserDAO.getAllResearchWorksOnArea(id,area,em))

7. Eliminar todos los datos de la base de datos para realizar otro testeo.(SistemaCacic.deleteAllData(em))
