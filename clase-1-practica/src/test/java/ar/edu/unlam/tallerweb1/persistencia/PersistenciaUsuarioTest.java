package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

public class PersistenciaUsuarioTest extends SpringTest {
    private Usuario usuario;
    private Session session;

    @Before
    public void setUp(){
        usuario = new Usuario();
        usuario.setEmail("email@empresa.com");
        usuario.setPassword("password");
        usuario.setRol("Usuario");

        session = getSession();
    }

    @Test
    @Transactional @Rollback()
    public void alGuardarUnUsuarioDeberiaEstarEnLaBase(){
        session.save(usuario); // Guarda el ID
        Usuario usuarioBuscado = session.get(Usuario.class, usuario.getId());

        assertThat (usuarioBuscado).isNotNull();
        assertThat(usuarioBuscado.getId()).isEqualTo(usuario.getId());
        assertThat(usuarioBuscado.getEmail()).isEqualTo(usuario.getEmail());
        assertThat(usuarioBuscado.getPassword()).isEqualTo(usuario.getPassword());
        assertThat(usuarioBuscado.getRol()).isEqualTo(usuario.getRol());
    }

    @Test
    @Transactional @Rollback()
    public void alActualizarUnUsuarioDeberiaEstarActualizadoEnLaBase(){
        session.save(usuario);

        usuario.setEmail("nuevo_email@empresa.com");
        usuario.setPassword("nueva_password");
        usuario.setRol("Administrador");

        session.save(usuario);

        Usuario usuarioBuscado = session.get(Usuario.class, usuario.getId());

        assertThat(usuarioBuscado.getId()).isEqualTo(usuario.getId());
        assertThat(usuarioBuscado.getEmail()).isEqualTo(usuario.getEmail());
        assertThat(usuarioBuscado.getPassword()).isEqualTo(usuario.getPassword());
        assertThat(usuarioBuscado.getRol()).isEqualTo(usuario.getRol());
    }

    @Test
    @Transactional @Rollback()
    public void alEliminarUnUsuarioNoDeberiaEstarEnLaBase(){
        session.save(usuario);
        session.delete(usuario);
        Usuario usuarioBuscado = session.get(Usuario.class, usuario.getId());
        assertThat(usuarioBuscado).isNull();
    }
}
