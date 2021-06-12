import static ratpack.groovy.Groovy.ratpack
import ratpack.thymeleaf3.ThymeleafModule
import static ratpack.thymeleaf3.Template.thymeleafTemplate

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

    // Serve assets from 'public'
    files { dir "public" }

  } //handlers
}
