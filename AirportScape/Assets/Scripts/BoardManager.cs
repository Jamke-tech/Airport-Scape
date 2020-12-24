using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine;
using Random = UnityEngine.Random;
using UnityEditor;

public class BoardManager : MonoBehaviour
{
    [Serializable]
    public class Count
    {
        public int minimum;
        public int maximum;
        public Count(int min, int max)
        {
            minimum = min;
            maximum = max;
        }
    }
    public int columns = 20;
    public int rows = 40;
    public GameObject[] floorTiles;
    public GameObject[] perimetralFloor;
    public GameObject[] enemyTiles;
    public GameObject[] externalWalls;
    public GameObject cleanersSprite;
    public GameObject playerSprite;
    public GameObject[] plants;
    public GameObject benchHorizontal;
    public GameObject benchVertical;
    public GameObject bin;
    public GameObject arrivals;
    public GameObject departures;
    public GameObject baggage;
    public GameObject[] companys;
    public GameObject secretWall;
    public GameObject fence;

    private GameObject mainCamera;

    private Transform boardHolder;
    private List<Vector3> gridPositionsCleanerPart1 = new List<Vector3>(); //To places the tiles
    public void Start()
    {
        //Buscamos la camara principal para asi poder assignar el personaje
        mainCamera = GameObject.FindWithTag("MainCamera");
    }


    void BoardSetup()
    {
        GameObject board = new GameObject("Board");
        ObjectFactory.AddComponent<CompositeCollider2D>(board);
        boardHolder = board.transform;
        boardHolder.GetComponent<Rigidbody2D>().bodyType = RigidbodyType2D.Static;
        GameObject playerinstance = new GameObject("Player");
        String mapa = "40 20\r\n"
        + "########################################\r\n"
        + "#        a          S                  #\r\n"
        + "#                   #                  #\r\n"
        + "#C                 V#V                 #\r\n"
        + "#                   #                  #\r\n"
        + "#                  V#V                 #\r\n"
        + "#                   #                  #\r\n"
        + "#C                 b#V          I      #\r\n"
        + "#                 Cp#p                 #\r\n"
        + "#                   #vvvvvvvvvvv  vvvvv#\r\n"
        + "#                   #                  #\r\n"
        + "#                   #                  #\r\n"
        + "#                 Cb#b                 #\r\n"
        + "# B B B B B B   B  V#V                 #\r\n"
        + "#           C       #                  #\r\n"
        + "# B B B   B B B B  V#V                 #\r\n"
        + "#C                  #                  #\r\n"
        + "# B B B B B B B B  V#V                 #\r\n"
        + "#                   #                  #\r\n"
        + "########################################\r\n";

        mapa = mapa.Replace("\r\n", "\n");
        String[] maplines = mapa.Split('\n');
        String[] mesures = maplines[0].Split(' ');
        int columns = Int32.Parse(mesures[0]);
        int rows = Int32.Parse(mesures[1]);

        for (int y = 0; y < rows; y++)
        {
            for (int x = 0; x < columns; x++)
            {
                char ch = maplines[y + 1][x];
                switch (ch)
                {
                    case '#': //instanciamos una external wall
                        GameObject toInstantiate = externalWalls[Random.Range(0, externalWalls.Length)];
                        GameObject instance = Instantiate(toInstantiate, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
                        instance.transform.SetParent(boardHolder);
                        break;
                    case 'S': //instanciamos una external wall secreta para mover
                        GameObject instanceSecretWall = Instantiate(secretWall, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                        instanceSecretWall.transform.SetParent(boardHolder);
                        intantiateFloor(x, rows - y, rows, columns);
                        break;
                    case 'v': //instanciamos una fence de cristal
                        GameObject instancefence = Instantiate(fence, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                        instancefence.transform.SetParent(boardHolder);
                        intantiateFloor(x, rows - y, rows, columns);
                        break;
                    case 'b'://papelera

                        GameObject instance2 = Instantiate(bin, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                        instance2.transform.SetParent(boardHolder);
                        intantiateFloor(x, rows-y,rows,columns);
                        break;

                    case 'B': //Banco Horizontal
                        GameObject instance3 = Instantiate(benchHorizontal, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                        instance3.transform.SetParent(boardHolder);
                        intantiateFloor(x, rows - y, rows, columns);
                        break;

                    case 'V'://Banco Vertical
                        GameObject instance4 = Instantiate(benchVertical, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                        instance4.transform.SetParent(boardHolder);
                        intantiateFloor(x, rows - y, rows, columns);
                        break;

                    case 'p': //planta
                        GameObject toInstantiate2 = plants[Random.Range(0, plants.Length)];
                        GameObject instance5 = Instantiate(toInstantiate2, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                        instance5.transform.SetParent(boardHolder);
                        intantiateFloor(x, rows - y, rows, columns);
                        break;
                    case 'd': //Cartel departures
                        GameObject instance6 = Instantiate(departures, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                        instance6.transform.SetParent(boardHolder);
                        intantiateFloor(x, rows - y, rows, columns);
                        break;
                    case 'a': //Cartel Arrivals
                        GameObject instance7 = Instantiate(arrivals, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                        instance7.transform.SetParent(boardHolder);
                        intantiateFloor(x, rows - y, rows, columns);
                        break;
                    case 'm': //Cartel baggage
                        GameObject instance8 = Instantiate(baggage, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                        instance8.transform.SetParent(boardHolder);
                        intantiateFloor(x, rows - y, rows, columns);
                        break;
                    case 'i': //Cartel de companyia aleatoria
                        GameObject toInstantiateCompanys = companys[Random.Range(0, companys.Length)];
                        GameObject instance9 = Instantiate(toInstantiateCompanys, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                        instance9.transform.SetParent(boardHolder);
                        intantiateFloor(x, rows - y, rows, columns);
                        break;

                    case 'C': //Cleaner                        
                        GameObject instance10 = Instantiate(cleanersSprite, new Vector3(x, rows - y, 0f), Quaternion.identity);
                        instance10.transform.SetParent(boardHolder);
                        intantiateFloor(x, rows - y, rows, columns);
                        break;
                    case 'I': //Inicio del Jugador                       
                        playerinstance = Instantiate(playerSprite, new Vector3(x, rows - y, 0f), Quaternion.identity);
                        intantiateFloor(x, rows - y, rows, columns);

                        break;
                    default:
                        intantiateFloor(x, rows - y, rows, columns);
                        break;

                }
            }


        }
        //Añadimos la camara de seguimiento del personage.

        CameraFollow camerascript = mainCamera.GetComponent<CameraFollow>();
        camerascript.following = playerinstance;
        //GameObject camerainstance =Instantiate(mainCamera, new Vector3(playerinstance.transform.position.x, playerinstance.transform.position.y, -10f), Quaternion.identity);
        

    }

    public void intantiateFloor(int x, int y, int rows, int columns)
    {
        GameObject floorToInstatiate = floorTiles[Random.Range(0, floorTiles.Length)];
        if (x == 1 || x == columns - 2 || y == 2 || y == rows - 1)//Ponemos el suelo perimetral
            floorToInstatiate = perimetralFloor[Random.Range(0, perimetralFloor.Length)];
        //Instanciamos el suelo sea el que sea
        GameObject instanceFloor = Instantiate(floorToInstatiate, new Vector3(x, y, 0f), Quaternion.identity) as GameObject;
        instanceFloor.transform.SetParent(boardHolder);
    }

    void LayoutObjectAtRandom(GameObject[] tileArray, int minimum, int maximum)
    {
        //Choose a random number of objects to instantiate within the minimum and maximum limits
        int objectCount = Random.Range(minimum, maximum + 1);

        //Instantiate objects until the randomly chosen limit objectCount is reached
        for (int i = 0; i < objectCount; i++)
        {
            //Choose a position for randomPosition by getting a random position from our list of available Vector3s stored in gridPosition
            Vector3 randomPosition = RandomPosition();

            //Choose a random tile from tileArray and assign it to tileChoice
            GameObject tileChoice = tileArray[Random.Range(0, tileArray.Length)];

            //Instantiate tileChoice at the position returned by RandomPosition with no change in rotation
            Instantiate(tileChoice, randomPosition, Quaternion.identity);
        }
    }
    Vector3 RandomPosition()
    {
        int randomIndex = Random.Range(0, gridPositionsCleanerPart1.Count);


        Vector3 randomPosition = gridPositionsCleanerPart1[randomIndex];


        gridPositionsCleanerPart1.RemoveAt(randomIndex);

        return randomPosition;
    }

    public void SetUpScene()
    {
        BoardSetup();
        //LayoutObjectAtRandom(cleanersSprite, cleanerCount.minimum, cleanerCount.maximum);

    }


}

