package fr.treeptik.bibliotheque.utils;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import fr.treeptik.bibliotheque.manager.PropertiesManager;

public class PropertiesWatcher extends Thread
{
	@Override
	public void run()
	{
		FileSystem fileSystem = FileSystems.getDefault();
		Path path = fileSystem.getPath(PropertiesManager.getProperties().getProperty("confFolder"));
		WatchService watchService = null;
		
		try
		{
			watchService = fileSystem.newWatchService();
			path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		WatchKey watchKey = null;
		
		do
		{
			try
			{
				watchKey = watchService.take();
			}
			
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				break;
			}
			
			List<WatchEvent<?>> watchEventList = watchKey.pollEvents();
			
			for(WatchEvent<?> watchEvent : watchEventList)
			{
				Path eventPath = (Path) watchEvent.context();
				String absolutePath = eventPath.toFile().getAbsolutePath();
				
				if(absolutePath.endsWith("conf.properties"))
				{
					PropertiesManager.loadMode();
					System.out.println("Fichier de configuration base de données modifié !");
				}
				
				if(absolutePath.endsWith("applicationcontext.xml"))
				{
					System.out.println("Fichier de configuration du contexte de l'application modifié !");
				}
			}
			
			try
			{
				sleep(500);
			}
			
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				break;
			}
		} 
		while (watchKey.reset());
	}
}
