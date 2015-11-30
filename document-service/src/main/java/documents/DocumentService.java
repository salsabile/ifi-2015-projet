package documents;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/documents")
public class DocumentService {

    @RequestMapping(value="{uuid}", method= RequestMethod.POST)
    public void postDocument(@PathVariable String uuid, @RequestParam("file") MultipartFile file){
        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(uuid)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
        }
    }

    @RequestMapping(value="{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getDocument(@PathVariable String uuid, HttpServletResponse response){
        response.setHeader( "Content-Disposition", "attachment;filename=" + uuid );
        return new FileSystemResource(new File(uuid));
    }

    @RequestMapping(value="{uuid}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDocument(@PathVariable String uuid){
        if( new File(uuid).delete() ){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
