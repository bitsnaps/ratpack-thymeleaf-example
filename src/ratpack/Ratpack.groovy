import static ratpack.groovy.Groovy.ratpack
import ratpack.server.BaseDir
import ratpack.form.Form
import ratpack.form.UploadedFile
import ratpack.thymeleaf3.ThymeleafModule
import static ratpack.thymeleaf3.Template.thymeleafTemplate
import java.nio.file.Paths

def uploadDir = 'uploads'
def publicDir = 'public'
def baseDir = BaseDir.find("${publicDir}/${uploadDir}")
def uploadPath = baseDir.resolve(uploadDir)


ratpack {
  serverConfig {
    port(3000)
    findBaseDir('thymeleaf')
  }

  bindings {
    module(ThymeleafModule)
  }

  handlers {

    get {
      render(thymeleafTemplate("home", [
              title:"Hello, Ratpack!",
              header:"Hello, Thymeleaf!",
              text:"This template got rendered using Thymeleaf",
              users: [
                [username:'admin', email:'admin@exmaple.com', role:'ADMIN'],
                [username:'demo', email:'demo@exmaple.com', role:'USER'],
                [username:'test', email:'test@exmaple.com', role:'USER']
                ]
              ]))

    } // get

    get('image/:id') {
      File imagePath = new File("${uploadPath}/${pathTokens['id']}")
      if (imagePath.exists()){
        render Paths.get(imagePath.toURI())
      } else {
        response.status(404).send('Image not found.')
      }
    }

    post ('upload') {
      parse(Form).then({ def form ->
        String username = form.get('username')
        UploadedFile file = form.file('avatar')
        File avatar = new File("${uploadPath}", file.fileName)
        file.writeTo(avatar.newOutputStream())
        println("Welcome ${username}, path: ${avatar.absolutePath}, exists: ${avatar.exists()}")
        render(thymeleafTemplate('profile', ['fileName': avatar.name]))
      })
    }

    // Serve assets from 'public'
    files { dir "public" }

  } //handlers
}
