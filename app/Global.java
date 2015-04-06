import models.Chamada;
import models.Cliente;
import models.GenericDAO;
import models.Usuario;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by caio on 24/03/15.
 */

public class Global extends GlobalSettings {

    private static GenericDAO dao = new GenericDAO();

    @Override
    public void onStart(Application app) {
        Logger.info("inicializada...");

        JPA.withTransaction(new play.libs.F.Callback0() {

            public void invoke() throws Throwable {

                List<Usuario> lis = dao.findAllByClassName(Usuario.class.getName());
                if (lis.size() == 0) {
                    Usuario u = new Usuario("Administrador", "administrador@administrador.com", "administrador",1,
                            "http://www.malibuhomesforsalerent.com/wp-content/themes/RealEstate/images/dummy/user.jpg");

                    Usuario t1 = new Usuario("Teste", "teste@teste.com","teste",0,
                            "http://www.malibuhomesforsalerent.com/wp-content/themes/RealEstate/images/dummy/user.jpg");

                    Usuario t2 = new Usuario("Teste 2", "teste2@teste2.com", "teste2",0,
                            "http://www.malibuhomesforsalerent.com/wp-content/themes/RealEstate/images/dummy/user.jpg");

                    dao.persist(u);
                    dao.persist(t1);
                    dao.persist(t2);
//
//                    for(int i=1; i<31; i++){
//                        Cliente cl = new Cliente("cliente"+i,"(83) 7895-8321","","","","","","","","","");
//                        dao.persist(cl);
//                        Calendar calendar = Calendar.getInstance();
//                        java.util.Date d = calendar.getTime();
//                        Date date = new Date(d.getYear(),d.getMonth(),i);
//                        Chamada ch = new Chamada(cl,"","","",0,t1,date,"","");
//                        dao.persist(ch);
//                    }
//
//                    for(int i=1; i<=31; i++){
//                        Cliente cli = new Cliente("cliente"+(i*31),"(83) 9999-9999","","","","","","","","","");
//                        dao.persist(cli);
//                        Calendar calendar = Calendar.getInstance();
//                        java.util.Date d = calendar.getTime();
//                        Date date = new Date(d.getYear(),(d.getMonth()-1),i);
//                        Chamada cha = new Chamada(cli,"","","",0,t2,date,"","");
//                        dao.persist(cha);
//                    }


                    dao.flush();
                    Logger.info("Inserindo dados no BD.");
                }
            }
        });
    }

    public void onStop(Application app) {
        Logger.info("desligada...");
    }

}