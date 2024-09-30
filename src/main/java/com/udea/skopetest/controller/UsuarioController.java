package com.udea.skopetest.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.udea.skopetest.dto.Usuario;
import com.udea.skopetest.service.IUsuarioService;
import com.udea.skopetest.util.reportes.PdfUsuario;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
    private IUsuarioService usuarioService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("eliminar-usuario/{usuarioId}")
    public String eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
    	return usuarioService.eliminarUsuario(usuarioId);
    }
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PutMapping("/actualizar-usuario")
	public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.ok().body(usuarioService.actualizarUsuario(usuario));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'AUDITOR')")
	@GetMapping("/lista-usuarios/{page}/{size}")
	public Map<String, Object> listarUsuarios(@PathVariable int page, @PathVariable int size){
    	Pageable paging = PageRequest.of(page, size);
		return usuarioService.listarUsuarios(paging);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'AUDITOR')")
	@GetMapping("/exportarPDF")
    public void exportarListadoUsuarios(HttpServletResponse response) throws DocumentException, IOException {
    	response.setContentType("application/pdf");
    	
    	//Indicamos el formato de fecha 
    	DateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    	String fechaActual = dateformatter.format(new Date());
    	
    	String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Usuarios_" + fechaActual + ".pdf";
    	
    	response.setHeader(cabecera, valor);
    	
    	List<Usuario> usuario = usuarioService.listarUsuarios();
    	
    	PdfUsuario exporter = new PdfUsuario(usuario);
    	exporter.exportarPDF(response);
    	
    }

}
