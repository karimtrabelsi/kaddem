package tn.esprit.projet.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.*;
import tn.esprit.projet.repository.FormationRepository;
import tn.esprit.projet.services.ICoursService;
import tn.esprit.projet.services.IEtudiantService;
import tn.esprit.projet.services.IFormationService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("Formation")
@Tag(name ="Formation" ,description = "Gestion des Formations ")
public class FormationController {
    @Autowired
    IFormationService FormationService;
    @Autowired
    ICoursService CoursService;
    @Autowired
    IEtudiantService etudiantService;
    @Autowired
    FormationRepository fr;

    @Autowired
    FormationController fc;

    @Operation(summary = "Get Formation", description = "Afficher liste des Formations ")
    @GetMapping("/getF")
    public List<Formation> GetF() {

        return FormationService.getAllFormation();
    }

    @Operation(summary = "Add Formation", description = "Ajouter des Formations ")
    @PostMapping("/addF")
    public void addFormation(@RequestBody Formation F) {
        FormationService.addFormation(F);
    }

    @Operation(summary = "Update Formation", description = "Modiifer des Formations ")
    @PutMapping("/putF/{idFormation}")
    public void updateC(@PathVariable("idFormation") Long id, @RequestBody Formation F) {

        F.setIdFormation(id);
        FormationService.updateFormation(F);
    }

    @Operation(summary = "Delete Formation", description = "Supprimer des Formations ")
    @DeleteMapping("/delF/{idFormation}")
    public void deleteF(@PathVariable("idFormation") Long id) {

        FormationService.deleteFormation(id);
    }

    @PostMapping ("/assignEtudiantToFormation/{etudiantId}/{formationId}")
    public void affectEtudiantToFormation(@PathVariable("etudiantId") Integer etudiantId, @PathVariable("formationId") Integer formationId)     {
        Etudiant e = etudiantService.getEtudiantbyid(etudiantId);
        Formation f = FormationService.getFormationbyid(formationId);
          //List<Formation> form = FormationService.getAllFormation();
        //FormationRepository.save(f);
        List<Etudiant> listE = f.getEtudiants();
        listE.add(e);
        f.setEtudiants(listE);
        sendSMS();
        fr.save(f);


    }


    @GetMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS() {

        Twilio.init("AC5ae12f720f77d17914b24ca11bb66341","99a7f6a3fb8bebe36fc83ba4f13b9314");

        Message.creator(new PhoneNumber("+21644196276"),
                new PhoneNumber("+12072887981"), "vous etes affecter avec succes 📞").create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }



    @PutMapping("archiverFormation/{idFormation}")
    public Formation archiverFormation(@PathVariable long idFormation) {
        return FormationService.archiverFormation(idFormation);
    }

    @Operation(summary = "findByArchive", description = "findByArchive")
    @GetMapping("findByArchive/{archive}")
    public List<Formation> findByArchive() {
        return FormationService.findByArchive();
    }

    @Operation(summary = "findByDate", description = "findByDate ")
    @GetMapping("/findByDate/{dateformation}")

    public Formation findByDate(@PathVariable("dateformation") @DateTimeFormat(pattern = "yyyy-MM-dd") Date  dateformation){

        return  FormationService.findByDate(dateformation);
    }

    @GetMapping("/countEtudiantByFormation/{idformation}")
    int countEtudiantByFormation(@RequestParam long idFormation ) {
        return FormationService.countEtudiantByFormation(idFormation);
    }


    }




