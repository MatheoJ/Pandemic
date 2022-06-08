import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class SoundEffect {
         
        //Clip pour la musique de fond
        Clip clipMusique;
        
        //Clip pour les sons autre 
        Clip clipSon ;
         
        public Clip setFile(String soundFileName){
             
            try{
                File file = new File(soundFileName);
                AudioInputStream sound = AudioSystem.getAudioInputStream(file); 
                Clip clip = AudioSystem.getClip();
                clip.open(sound);
                
                return clip;
            }
            catch(Exception e){
               return null;  
            }
        } 
          
        //Joue le nom du fichier mit en paramètre
        public void play(String fileName){
            clipSon = setFile(fileName);
            clipSon.setFramePosition(0);
            clipSon.start();
            
        }
        
        //Met en pause la musique de fond si elle est active, sinon, relance la lecture
        public void stop(){
			if (clipMusique.isRunning()){
				clipMusique.stop();
			}
			else {
				clipMusique.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
        
        //Lance la msusique de fond 
        public void backGroundMusic(String fileName){
			
			clipMusique = setFile(fileName);
			clipMusique.setFramePosition(0);
            clipMusique.loop(Clip.LOOP_CONTINUOUSLY);
		}
 }
