using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraFollow : MonoBehaviour
{
    public GameObject following;
    public int maxX;
    public int maxY;

    public void Start()
    {
        maxX = GameManager.instance.boardScript.columns;
        maxY = GameManager.instance.boardScript.rows;
    }
    
    
    void FixedUpdate()
    {
        float posX = following.transform.position.x;
        float posY = following.transform.position.y;
        transform.position = new Vector3(Mathf.Clamp(posX, 0, maxX),
            Mathf.Clamp(posY, 0, maxY),
            transform.position.z);



    }
    public void SetFollowing(GameObject player)
    {
        following = player;
    }
}
