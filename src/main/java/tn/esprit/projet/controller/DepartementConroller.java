package tn.esprit.projet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.services.IDepartementService;

@RestController
@RequestMapping("/SpringMVC/servlet")
public class DepartementConroller {
	@Autowired
	IDepartementService idd; // pour assurer le couplage faible

	// http://localhost:8088/SpringMVC/servlet/retrieveAllDepartement
	@CrossOrigin("*")
	@GetMapping("/retrieveAllDepartement")
	@ResponseBody
	public List<Departement> retrieveAllDepartement() {
		List<Departement> listID = idd.retrieveAllDepartement();
		return listID;
	}

	// http://localhost:8088/SpringMVC/servlet/addDepartement
	@CrossOrigin("*")
	@PostMapping("/addDepartement")
	@ResponseBody
	public Departement addDepartement(@RequestBody Departement df) {
		Departement addIU = idd.addDepartement(df);
		return addIU;

	}

	// http://localhost:8088/SpringMVC/servlet/deleteDepartement/{id}
	@CrossOrigin("*")
	@DeleteMapping("/deleteDepartement/{id}")
	@ResponseBody
	public void deleteDepartement(@PathVariable("id") Integer id) {
		idd.deleteDepartement(id);
	}

	// http://localhost:8088/SpringMVC/servlet/updateDepartement
	@CrossOrigin("*")
	@PutMapping("/updateDepartement")
	@ResponseBody
	public Departement updateDepartement(@RequestBody Departement un) {
		return idd.updateDepartement(un);

	}

	// http://localhost:8088/SpringMVC/servlet/retrieveDepartement/{id}
	@CrossOrigin("*")
	@GetMapping("/retrieveDepartement/{id}")
	@ResponseBody
	public Departement retrieveDepartement(@PathVariable("id") Integer id) {
		Departement un = idd.retrieveDepartement(id);
		return un;
	}
	
	// http://localhost:8088/SpringMVC/servlet/retrieveDepartementByName?keyword=
	@CrossOrigin("*")
	@RequestMapping("/retrieveDepartementByName")
    public List<Departement> retrieveDepartementByName(@Param("keyword") String keyword) {
        List<Departement> listProducts = idd.listAll(keyword);
         
        return listProducts;
    }

}
