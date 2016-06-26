package br.com.caelum.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.loja.models.CarrinhoCompras;
import br.com.caelum.loja.models.DadosServico;
import br.com.caelum.loja.models.Usuario;

@Controller
@RequestMapping("/pagamento")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {

    @Autowired
    private CarrinhoCompras carrinho;

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private MailSender sender;

    @RequestMapping(value="/finalizar",method=RequestMethod.POST)
    public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model) {
	return () -> {
	    String uri = "http://book-payment.herokuapp.com/payment";
	    String resposta = restTemplate.postForObject(uri, new DadosServico(carrinho.getTotal()), String.class);

	    System.out.println(resposta);
	    this.enviaEmailCompraProduto(usuario);
	    
	    model.addFlashAttribute("mensagem", resposta);
	    return new ModelAndView("redirect:/produtos");
	};
    }

    private void enviaEmailCompraProduto(Usuario usuario) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Compra finalizada com sucesso!");
        //email.setTo(usuario.getEmail());
        email.setTo("lg_paro@hotmail.com"); // Your email here!!!
        email.setText("Compra aprovada com sucesso no valor de " + this.carrinho.getTotal());
        email.setFrom("compras@casadocodigo.com.br");
        
        this.sender.send(email);
    }
}