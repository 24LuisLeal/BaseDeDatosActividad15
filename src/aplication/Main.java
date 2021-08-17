package aplication;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


/**
 * 
 * @author HungryStudentDev
 */

public class Main {

    public static void main(String[] args) throws SQLException {
        
        //Dirección de la base datos
        String url = "TU DIRECCIÓN DE LA BASE DE DATOS";
        //Usuario 
        String user = "TU USUARIO";
        //Contraseña
        String password = "YOUR PASSWORD";
        //Referencia para realizar la conexión
        Connection myConnection = null;
        //Petición para hacer cambios a la base de datos
        PreparedStatement ps;
        //Capturar datos
        Scanner sc = new Scanner(System.in);

        //Hacer la conexión con la base de datos
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myConnection = (Connection) DriverManager.getConnection(url, 
            user, password);
            System.out.println("    Conexión exitosa");
        } catch (Exception e) {
            System.err.println("    Conexión fallida a la base de datos. "
                    + "Error de tipo: "+e);
        }
        
        //Desplegar menú de la aplicación
        System.out.println("    Base de Datos de Autores");
        
        //Declaración del objeto autor
        Autor autor = new Autor();
        
        
        //Entrada de la opción del usuario
        int option;
        //Realiza
        do{
            System.out.println("");
            System.out.println("    Digite el número de la opción a realizar");
            System.out.println("");
            System.out.println("    1. Insertar un nuevo autor.");
            System.out.println("");
            System.out.println("    2. Eliminar un autor");
            System.out.println("");
            System.out.println("    3. Ver datos de un autor");
            System.out.println("");
            System.out.println("    4. Modificar datos de un autor");
            System.out.println("");
            System.out.println("    5. Salir");
            System.out.println("");
            System.out.print("    >>");
            option = sc.nextInt();

            switch(option){
                case 1: {
                    System.out.println("    Rellene los datos del nuevo autor");
                    System.out.println("");
                    
                    System.out.print("    Nombre: ");
                    String name = sc.next();
                    autor.setName(name);
                    
                    System.out.print("    Apellido Paterno: ");
                    String lastName = sc.next();
                    autor.setLastName(lastName);
                    
                    //Sentencia MySQL
                    ps = myConnection.prepareStatement("insert into autores"
                                                               //1,2,3
                    + "(idAutor, nombre, apellidoPaterno) values(?,?,?)");
                    ps.setString(1, null);
                    ps.setString(2, autor.getName());
                    ps.setString(3, autor.getLastName());

                    int result = ps.executeUpdate();

                    if(result > 0){
                        System.out.println("");
                        System.out.println("    Datos aregados correctamente");
                        System.out.println("");
                    }else{
                        System.out.println("");
                        System.out.println("    No se logro agregar al "
                                + "nuevo autor");
                        System.out.println("");
                    }

                    Statement st = (Statement) myConnection.createStatement();
                    ResultSet rsbd = st.executeQuery("select * from autores");

                    //Muestro la tabla actualizada
                    System.out.println("    Id Autor\t\tNombre"
                            + "\t\tApellido Paterno");
                    System.out.println("");

                    while(rsbd.next()){
                        System.out.println("    "+rsbd.getInt("idAutor")+
                                "\t\t\t"+rsbd.getString(2)+
                                "\t\t"+rsbd.getString(3));
                    }
                    System.out.println("");
                }break;

                case 2: {
                    System.out.println("    "
                    + "Proporcione el ID del autor a eliminar");
                    System.out.println("");
                    Statement st1 = (Statement) myConnection.createStatement();
                    ResultSet rsbd1 = st1.executeQuery("select * from autores");

                    //Muestro la tabla actualizada
                    System.out.println("    Id Autor\t\tNombre"
                            + "\t\tApellido Paterno");

                    while(rsbd1.next()){
                        System.out.println("    "+rsbd1.getInt("idAutor")+
                                "\t\t\t"+rsbd1.getString(2)+
                                "\t\t"+rsbd1.getString(3));
                    }
                    System.out.println("");
                    System.out.print("    ID del autor: ");
                    int id = sc.nextInt();
                    
                    //Sentencia MySQL
                    ps = myConnection.prepareStatement(""
                    + "delete from autores where idAutor=?");
                    
                    ps.setInt(1, id);

                    int result = ps.executeUpdate();

                    if(result > 0){
                        System.out.println("");
                        System.out.println("    "
                        + "Datos eliminados correctamente");
                        System.out.println("");
                    }else{
                        System.out.println("");
                        System.out.println("    No se logro eliminar el autor");
                        System.out.println("");
                    }

                    Statement st = (Statement) myConnection.createStatement();
                    ResultSet rsbd = st.executeQuery("select * from autores");

                    //Muestro la tabla actualizada
                    System.out.println("    Id Autor\t\tNombre"
                            + "\t\tApellido Paterno");
                    System.out.println("");

                    while(rsbd.next()){
                        System.out.println("    "+rsbd.getInt("idAutor")+
                                "\t\t\t"+rsbd.getString(2)+
                                "\t\t"+rsbd.getString(3));
                    }
                    System.out.println("");
                }break;

                case 3: {
                    System.out.println("    "
                    + "Proporcione el ID del autor a buscar");
                    System.out.println("");
                    System.out.print("    ID del autor: ");
                    int id = sc.nextInt();

                    //Indico el id del autor
                    ps = myConnection.prepareStatement("select * from autores "
                            + "where idAutor=?");
                    ps.setInt(1, id);
                    //Realizo la consulta
                    ResultSet rsbd = ps.executeQuery();
                    //Muestro los datos
                    System.out.println("    Id Autor\t\tNombre"
                            + "\t\tApellido Paterno");
                    System.out.println("");

                    if(rsbd.next()){
                        System.out.println("    "+rsbd.getInt("idAutor")+
                                "\t\t\t"+rsbd.getString(2)+
                                "\t\t"+rsbd.getString(3));
                        System.out.println("");
                    }else{
                        System.out.println("");
                        System.out.println("    No existe ningún registro "
                                + "con ese ID");
                        System.out.println("");
                    }

                }break;

                case 4: {
                    System.out.println("    "
                    + "Proporcione el ID del autor a editar");
                    System.out.println("");
                    System.out.print("    ID del autor: ");
                    int id = sc.nextInt();

                    System.out.println("");

                    System.out.println("    Rellene los datos del autor");
                    System.out.println("");

                    System.out.print("    Nombre: ");
                    String name = sc.next();

                    System.out.print("    Apellido Paterno: ");
                    String lastName = sc.next();
                    System.out.println("");

                    ps = myConnection.prepareStatement("update autores set"
                    + " nombre=?, apellidoPaterno=? where idAutor ="+id);
                    ps.setString(1, name);
                    ps.setString(2, lastName);

                    int result = ps.executeUpdate();

                    if(result > 0){
                        System.out.println("");
                        System.out.println("    Datos aregados correctamente");
                        System.out.println("");
                    }else{
                        System.out.println("");
                        System.out.println("    No se logro agregar al "
                                + "nuevo autor");
                        System.out.println("");
                    }

                    Statement st = (Statement) myConnection.createStatement();
                    ResultSet rsbd = st.executeQuery("select * from autores");

                    //Muestro la tabla actualizada
                    System.out.println("    Id Autor\t\tNombre"
                            + "\t\tApellido Paterno");
                    System.out.println("");
                    while(rsbd.next()){
                        System.out.println("    "+rsbd.getInt("idAutor")+
                                "\t\t\t"+rsbd.getString(2)+
                                "\t\t"+rsbd.getString(3));
                    }
                    System.out.println("");
                }break;
                
                case 5:{
                    Statement st = (Statement) myConnection.createStatement();
                    ResultSet rsbd = st.executeQuery("select * from autores");

                    //Muestro la tabla actualizada
                    System.out.println("    Id Autor\t\tNombre"
                            + "\t\tApellido Paterno");
                    System.out.println("");
                    while(rsbd.next()){
                        System.out.println("    "+rsbd.getInt("idAutor")+
                                "\t\t\t"+rsbd.getString(2)+
                                "\t\t"+rsbd.getString(3));
                    }
                    System.out.println("");
                }break;
                default:{
                    System.out.println("");
                    System.out.println("    Digite una opción valida");
                    System.out.println("");
                }
            }
            //Mientras la opción de entrada sea diferente de 5
        } while(option != 5);
    }
}
