using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine;
using Random = UnityEngine.Random;

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


    public int rows = 20;
    public int columns = 40;
    public GameObject[] floorTiles;
    public GameObject[] perimetralFloor;
    public GameObject[] enemyTiles;
    public GameObject[] externalWalls;
    public GameObject[] cleanersSprite;
    public GameObject benchHorizontal;
    public GameObject benchVertical;
    public Count cleanerCount = new Count(2, 2);

    private Transform boardHolder;
    private List<Vector3> gridPositionsCleanerPart1 = new List<Vector3>(); //To places the tiles


    void InitialiseList()
    {
        gridPositionsCleanerPart1.Clear();
        for (int x = 2; x < columns-2; x++)//Modificar depenent del que volem posar en la grid 
        {
            for (int y = 2; y < rows - 2; y++)
            {
                 gridPositionsCleanerPart1.Add(new Vector3(x, y, 0f));
                
            }
        }
    }

    void BoardSetup()
    {
        boardHolder = new GameObject("Board").transform;

        for (int x = -1; x < columns + 1; x++)//Per establir el terra
        {
            for (int y = -1; y < rows + 1; y++)
            {
                GameObject toInstantiate = floorTiles[Random.Range(0, floorTiles.Length)];

                if (x == -1 || x == columns || y == -1 || y == rows)
                    toInstantiate = externalWalls[Random.Range(0, externalWalls.Length)];
                else if (x == 0 || x == columns - 1 || y == 0 || y == rows - 1)
                    toInstantiate = perimetralFloor[Random.Range(0, perimetralFloor.Length)];

                GameObject instance = Instantiate(toInstantiate, new Vector3(x, y, 0f), Quaternion.identity) as GameObject;
                instance.transform.SetParent(boardHolder);
            }
        }

        //Posem la primera part d'escenari ( la casella 20x20)

        int[] positionsBenchLines = new int[] { 1, 3, 5, 7, 9, 11, 13, 15 };
        int[] positionsBenchHorizontal= new int[] { 1, 3, 5, 7, 13, 15 };
        int[] positionBinx = new int[] { };
        int[] positionBiny = new int[] { };
        int[] positionSignalsx = new int[] { };
        int[] positionSignalsy = new int[] { };



        for (int posX = 0; posX < columns; posX++)
        {
            for (int posY = 0; posY < rows; posY++)
            {
                if (posX <= columns/2)//primera part de la casella
                {
                    if (posX == (columns / 2) )//Posem bancs verticals de separacio
                    {
                        if (ExistInVector(positionsBenchHorizontal, posY))
                        {
                            //Instanciem el banc en la posicio que tenim
                            Vector3 benchposition = new Vector3(posX, posY, 0f);

                            Instantiate(benchVertical, benchposition, Quaternion.identity);

                        }

                    }
                    else if (posY == 2 || posY == 5)//Posem bancs en fila
                    {
                        if (ExistInVector(positionsBenchLines, posX))
                        {
                            if (posX != 5 || posY != 5)//eliminamos el banco de la posicion 5,5
                            {
                                //Instanciem el banc en la posicio que tenim
                                Vector3 benchposition = new Vector3(posX, posY, 0f);

                                Instantiate(benchHorizontal, benchposition, Quaternion.identity);
                            }
                        }
                    }
                }
                else//segona part del mapa
                {
                    if (posX == (columns / 2)+1)//Posem bancs verticals de separacio al cosat del altres
                    {
                        if (ExistInVector(positionsBenchHorizontal, posY))
                        {
                            //Instanciem el banc en la posicio que tenim
                            Vector3 benchposition = new Vector3(posX, posY, 0f);

                            Instantiate(benchVertical, benchposition, Quaternion.identity);

                        }

                    }

                }
            }

        }



        // Anem a dissenyar la zona intermitja 

    }


    private bool ExistInVector(int[] Vector, int num)
    {
        bool exist = false;
        int i = 0;
        while (i < Vector.Length && exist != true)
        {
            if (Vector[i] == num)
            {
                exist = true;
            }
            if (!exist)
            {
                i++;
            }

        }
        return exist;
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
        InitialiseList();
        BoardSetup();
        LayoutObjectAtRandom(cleanersSprite, cleanerCount.minimum, cleanerCount.maximum);

    }


}

